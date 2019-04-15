package com.naosim.dddwork.datasource;

import com.google.common.collect.ImmutableList;
import com.naosim.dddwork.domain.Attendance;
import com.naosim.dddwork.domain.AttendanceFactory;
import com.naosim.dddwork.domain.AttendanceRepository;
import com.naosim.dddwork.domain.TimePoint;
import com.naosim.dddwork.domain.WorkMinute;
import com.naosim.dddwork.domain.YearMonth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Predicate;

@Repository
public class AttendanceRepositoryCsv implements AttendanceRepository {

    private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    private final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final static DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    private final CsvSetting setting;
    private final FileIOHelper fileIOHelper;
    private final AttendanceFactory attendanceFactory;

    @Autowired
    public AttendanceRepositoryCsv(CsvSetting setting, FileIOHelper fileIOHelper, AttendanceFactory attendanceFactory) {
        this.setting = setting;
        this.fileIOHelper = fileIOHelper;
        this.attendanceFactory = attendanceFactory;
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

    private List<Attendance> filterMonthly(List<Attendance> attendances, YearMonth yearMonth) {
        Predicate<Attendance> condition = (Attendance attendance) ->
                attendance.getDate().getYear() == yearMonth.getYear()
                        && attendance.getDate().getMonthValue() == yearMonth.getMonth();

        return attendances.stream()
                .filter(condition)
                .collect(ImmutableList.toImmutableList());
    }

    private String serialize(Attendance attendance) {
        return String.format(
                "%s,%s,%s,%s,%s,%s",
                attendance.getDate().format(DATE_FORMATTER),
                attendance.getStartTime().getValue().format(TIME_FORMATTER),
                attendance.getEndTime().getValue().format(TIME_FORMATTER),
                attendance.getWorkMinute().getValue(),
                attendance.getOverWorkMinute().getValue(),
                attendance.getCreateAt().format(DATE_TIME_FORMATTER)
        );
    }

    private Attendance deserialize(String line) {
        String[] items = line.split(",");
        String date = items[0];
        String startDate = items[1];
        String endDate = items[2];
        String workMinute = items[3];
        String overWorkMinute = items[4];
        String createAt = items[5];

        return attendanceFactory.create(
                LocalDate.parse(date, DATE_FORMATTER),
                new TimePoint(LocalTime.parse(startDate, TIME_FORMATTER)),
                new TimePoint(LocalTime.parse(endDate, TIME_FORMATTER)),
                new WorkMinute(Integer.valueOf(workMinute)),
                new WorkMinute(Integer.valueOf(overWorkMinute)),
                LocalDateTime.parse(createAt, DATE_TIME_FORMATTER)
        );
    }
}
