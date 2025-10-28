package org.example.datasource;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import org.example.domain.TimestampOfTheRegistration;
import org.example.domain.TotalHours;
import org.example.domain.WorkEndTime;
import org.example.domain.TotalOverTimeHours;
import org.example.domain.WorkStartTime;
import org.example.domain.DateToRegister;
import org.example.domain.WorkInformation;
import org.example.domain.WorkTime;
import org.example.domain.TotalWorkingHours;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.example.utils.DateTimeFormatters.HOUR_MINUTE;
import static org.example.utils.DateTimeFormatters.YEAR_MONTH;
import static org.example.utils.DateTimeFormatters.YEAR_MONTH_DAY;

@Component
@RequiredArgsConstructor
public class WorkInformationCSVMapper {
    @Value("${csv.database.path}")
    private String csvFileName;

    public void insert(WorkInformation workInformation) {
        try {
            File csvFile = new File(csvFileName);

            if(!csvFile.getParentFile().exists() && !csvFile.getParentFile().mkdirs()){
                throw new RuntimeException("Failed to create directories: " + csvFile.getParent());
            }

            try (CSVWriter csvWriter = new CSVWriter(new FileWriter(csvFileName, true))) {
                csvWriter.writeNext(toCVString(workInformation));
            } catch (Exception err) {
                throw new RuntimeException(err);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional
            <List<Optional<TotalHours>>> findByMonth(YearMonth month) {
        try {
            File csvFile = new File(csvFileName);
            if (!csvFile.exists()) {
                return Optional.of(List.of());
            }

            try (CSVReader csvReader = new CSVReader(new FileReader(csvFileName))) {
                return Optional.of(csvReader.readAll().stream()
                                           .map(this::toWorkInformation)
                                           .filter(workInformation -> workInformation.getDateToRegister()
                                                   .getValue()
                                                   .format(YEAR_MONTH_DAY)
                                                   .startsWith(month.format(YEAR_MONTH)))
                                           .collect(Collectors.groupingBy(
                                                   workInformation -> workInformation.getDateToRegister().getValue()
                                           ))
                                           .values().stream()
                                           .map(group -> group.stream()
                                                   .max(Comparator.comparing(wi -> wi.getTimestampOfTheRegistration()
                                                           .getValue()))
                                                   .map(this::toTotalHours))
                                           .toList());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String[] toCVString(WorkInformation workInformation) {
        return new String[]{
                workInformation.getDateToRegister().getValue().format(YEAR_MONTH_DAY),
                workInformation.getWorkTime().getWorkStartTime().getValue().format(HOUR_MINUTE),
                workInformation.getWorkTime().getWorkEndTime().getValue().format(HOUR_MINUTE),
                String.valueOf(workInformation.getTotalHours().getTotalWorkingHours().getValue()),
                String.valueOf(workInformation.getTotalHours().getTotalOverTimeHours().getValue()),
                workInformation.getTimestampOfTheRegistration().getValue().toString()
        };
    }

    private WorkInformation toWorkInformation(String[] values) {
        return new WorkInformation(
                new WorkTime(
                        new WorkStartTime(
                                LocalTime.parse(values[1].substring(0, 4), HOUR_MINUTE)
                        ),
                        new WorkEndTime(
                                LocalTime.parse(values[2].substring(0, 4), HOUR_MINUTE)
                        )
                ),
                new TotalHours(
                        new TotalWorkingHours(Integer.parseInt(values[3])),
                        new TotalOverTimeHours(Integer.parseInt(values[4]))
                ),
                new DateToRegister(LocalDate.parse(values[0], YEAR_MONTH_DAY)),
                new TimestampOfTheRegistration(LocalDateTime.parse(values[5]))

        );
    }

    private TotalHours toTotalHours(WorkInformation wi) {
        return new TotalHours(
                new TotalWorkingHours(wi.getTotalHours().getTotalWorkingHours().getValue()),
                new TotalOverTimeHours(wi.getTotalHours().getTotalOverTimeHours().getValue())
        );
    }
}
