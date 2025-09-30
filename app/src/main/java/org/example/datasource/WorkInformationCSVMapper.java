package org.example.datasource;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import org.example.domain.CreateTimestamp;
import org.example.domain.Date;
import org.example.domain.EndTime;
import org.example.domain.Hour;
import org.example.domain.Minute;
import org.example.domain.Month;
import org.example.domain.Overtime;
import org.example.domain.StartTime;
import org.example.domain.TargetDate;
import org.example.domain.Time;
import org.example.domain.Timestamp;
import org.example.domain.WorkInformation;
import org.example.domain.WorkTime;
import org.example.domain.WorkingHours;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class WorkInformationCSVMapper {
    @Value("${csv.database.path}")
    private String csvFileName;

    public void insert(WorkInformation workInformation) {
        try {
            File csvFile = new File(csvFileName);

            csvFile.getParentFile().mkdirs();

            try (CSVWriter csvWriter = new CSVWriter(new FileWriter(csvFileName, true))) {
                csvWriter.writeNext(toSCVString(workInformation));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional
            <List<Optional<WorkInformation>>> findByMonth(Month month) {
        try {
            File csvFile = new File(csvFileName);
            if (!csvFile.exists()) {
                return Optional.of(List.of());
            }

            try (CSVReader csvReader = new CSVReader(new FileReader(csvFileName))) {
                return Optional.of(csvReader.readAll().stream()
                                           .map(this::toWorkInformation)
                                           .filter(workInformation -> workInformation.getTargetDate()
                                                   .getDate()
                                                   .getValue()
                                                   .startsWith(month.getValue()))
                                           .collect(Collectors.groupingBy(
                                                    workInformation -> workInformation.getTargetDate().getDate().getValue()
                                           ))
                                           .values().stream().map(group -> group.stream()
                                                   .max(Comparator.comparing(wi -> wi.getCreateTimestamp().getTimestamp().getValue())))
                                           .toList());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private WorkInformation toWorkInformation(String[] values) {
        return new WorkInformation(
                new WorkTime(
                        new WorkingHours(new Time(Integer.parseInt(values[3]))),
                        new Overtime(new Time(Integer.parseInt(values[4]))),
                        new StartTime(
                                new Hour(Integer.parseInt(values[1].substring(0, 2))),
                                new Minute(Integer.parseInt(values[1].substring(2, 4)))
                        ),
                        new EndTime(
                                new Hour(Integer.parseInt(values[2].substring(0, 2))),
                                new Minute(Integer.parseInt(values[2].substring(2, 4)))
                        )
                ),
                new TargetDate(new Date(values[0])),
                new CreateTimestamp(Timestamp.of(values[5]))

        );
    }

    private static String[] toSCVString(WorkInformation workInformation) {
        return new String[] {
                workInformation.getTargetDate().getDate().getValue(),
                workInformation.getWorkTime().getStartTime().getStartHours().getFormatHour()
                        + workInformation.getWorkTime().getStartTime().getStartMinutes().getFormatMinute(),
                workInformation.getWorkTime().getEndTime().getEndHours().getFormatHour()
                        + workInformation.getWorkTime().getEndTime().getEndMinutes().getFormatMinute(),
                String.valueOf(workInformation.getWorkTime().getWorkingHours().getTime().getValue()),
                String.valueOf(workInformation.getWorkTime().getOvertime().getTime().getValue()),
                workInformation.getCreateTimestamp().getTimestamp().getValue().toString()
        };
    }
}
