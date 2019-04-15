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

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Predicate;

@Repository
public class AttendanceRepositoryCsv implements AttendanceRepository {

    private final static Charset CHARSET = StandardCharsets.UTF_8;
    private final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final static DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    private final CsvSetting setting;
    private final AttendanceFactory attendanceFactory;

    @Autowired
    public AttendanceRepositoryCsv(CsvSetting setting, AttendanceFactory attendanceFactory) {
        this.setting = setting;
        this.attendanceFactory = attendanceFactory;
    }

    @Override
    public void save(Attendance attendance) {
        try {
            Files.write(
                    getTargetFile().toPath(),
                    ImmutableList.of(serialize(attendance)),
                    CHARSET,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Attendance> fetchMonthly(YearMonth yearMonth) {
        Predicate<Attendance> condition = (Attendance attendance) ->
                attendance.getDate().getYear() == yearMonth.getYear()
                        && attendance.getDate().getMonthValue() == yearMonth.getMonth();
        return fetchAll().stream().filter(condition).collect(ImmutableList.toImmutableList());
    }

    private List<Attendance> fetchAll() {
        try {
            return Files.lines(getTargetFile().toPath(), CHARSET)
                    .map(this::deserialize)
                    .collect(ImmutableList.toImmutableList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private File getTargetFile() {
        return new File(setting.getFilepath());
    }

    private String serialize(Attendance attendance) {
        return String.format(
                "%s,%s,%s,%s,%s,%s\n",
                attendance.getDate().format(DATE_FORMATTER),
                attendance.getStartTime().getValue().format(TIME_FORMATTER),
                attendance.getEndTime().getValue().format(TIME_FORMATTER),
                attendance.getWorkMinute().getValue(),
                attendance.getOverWorkMinute().getValue(),
                attendance.getCreateDate().format(DATE_FORMATTER)
        );
    }

    private Attendance deserialize(String line) {
        String[] items = line.split(",");
        String date = items[0];
        String startDate = items[1];
        String endDate = items[2];
        String workMinute = items[3];
        String overWorkMinute = items[4];
        String createDate = items[5];

        return attendanceFactory.create(
                LocalDate.parse(date, DATE_FORMATTER),
                new TimePoint(LocalTime.parse(startDate, TIME_FORMATTER)),
                new TimePoint(LocalTime.parse(endDate, TIME_FORMATTER)),
                new WorkMinute(Integer.valueOf(workMinute)),
                new WorkMinute(Integer.valueOf(overWorkMinute)),
                LocalDate.parse(createDate, DATE_FORMATTER)
        );
    }
}
