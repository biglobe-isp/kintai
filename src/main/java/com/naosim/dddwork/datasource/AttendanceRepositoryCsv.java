package com.naosim.dddwork.datasource;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableList;
import com.naosim.dddwork.domain.Attendance;
import com.naosim.dddwork.domain.AttendanceRepository;
import com.naosim.dddwork.domain.TimePoint;
import com.naosim.dddwork.domain.WorkMinute;
import com.naosim.dddwork.domain.YearMonth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Repository
public class AttendanceRepositoryCsv implements AttendanceRepository {

    private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    private final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final CsvSetting setting;
    private final FileIOHelper fileIOHelper;

    @Autowired
    public AttendanceRepositoryCsv(CsvSetting setting, FileIOHelper fileIOHelper) {
        this.setting = setting;
        this.fileIOHelper = fileIOHelper;
    }

    @Override
    public void save(Attendance attendance) {
        fileIOHelper.writeLines(setting.getFilepath(), ImmutableList.of(serialize(attendance)));
    }

    @Override
    public List<Attendance> fetchMonthly(YearMonth yearMonth) {
        List<Attendance> attendances = fileIOHelper.readLines(setting.getFilepath())
                .stream()
                .map(this::deserialize)
                .collect(ImmutableList.toImmutableList());
        return filterMonthly(attendances, yearMonth);
    }

    @VisibleForTesting
    List<Attendance> filterMonthly(List<Attendance> attendances, YearMonth yearMonth) {
        Predicate<Attendance> condition = (attendance) ->
                attendance.getDate().getYear() == yearMonth.getYear()
                        && attendance.getDate().getMonthValue() == yearMonth.getMonth();

        Collector<Attendance, ?, Map<LocalDate, Attendance>> aggregateByMaxDate = Collectors.toMap(
                Attendance::getDate,
                Function.identity(),
                BinaryOperator.maxBy(Comparator.comparing(Attendance::getCreateAt))
        );

        Map<LocalDate, Attendance> map = attendances.stream()
                .filter(condition)
                .collect(aggregateByMaxDate);

        return map.values().stream().collect(ImmutableList.toImmutableList());
    }

    @VisibleForTesting
    String serialize(Attendance attendance) {
        return String.format(
                "%s,%s,%s,%s,%s,%s",
                attendance.getDate().format(DATE_FORMATTER),
                attendance.getStartTime().toString(),
                attendance.getEndTime().toString(),
                attendance.getWorkMinute().getValue(),
                attendance.getOverWorkMinute().getValue(),
                attendance.getCreateAt().format(DATE_TIME_FORMATTER)
        );
    }

    @VisibleForTesting
    Attendance deserialize(String line) {
        String[] items = line.split(",");
        String date = items[0];
        String startDate = items[1];
        String endDate = items[2];
        String workMinute = items[3];
        String overWorkMinute = items[4];
        String createAt = items[5];

        return Attendance.builder()
                .date(LocalDate.parse(date, DATE_FORMATTER))
                .startTime(TimePoint.parse(startDate))
                .endTime(TimePoint.parse(endDate))
                .workMinute(WorkMinute.parse(workMinute))
                .overWorkMinute(WorkMinute.parse(overWorkMinute))
                .createAt(LocalDateTime.parse(createAt, DATE_TIME_FORMATTER))
                .build();
    }
}
