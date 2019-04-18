package com.naosim.dddwork.datasource;

import com.google.common.collect.ImmutableList;
import com.naosim.dddwork.domain.Attendance;
import com.naosim.dddwork.domain.TimePoint;
import com.naosim.dddwork.domain.WorkMinute;
import com.naosim.dddwork.domain.YearMonth;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class AttendanceRepositoryCsvTest {

    @Test
    public void testFilterMonthly1() {
        AttendanceRepositoryCsv target = createDefaultTarget();
        YearMonth yearMonth = new YearMonth(2019, 4);
        List<Attendance> attendances = ImmutableList.of();
        List<Attendance> expected = ImmutableList.of();

        List<Attendance> actual = target.filterMonthly(attendances, yearMonth);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testFilterMonthly2() {
        AttendanceRepositoryCsv target = createDefaultTarget();
        YearMonth yearMonth = new YearMonth(2019, 4);
        List<Attendance> attendances = ImmutableList.of(
                createAttendance(4, 1, 4, 1),
                createAttendance(4, 2, 4, 2),
                createAttendance(4, 3, 4, 3),
                createAttendance(5, 1, 5, 1),
                createAttendance(5, 2, 5, 2),
                createAttendance(5, 3, 5, 3)
        );
        List<Attendance> expected = ImmutableList.of(
                createAttendance(4, 1, 4, 1),
                createAttendance(4, 2, 4, 2),
                createAttendance(4, 3, 4, 3)
        );

        List<Attendance> actual = target.filterMonthly(attendances, yearMonth);

        assertThat(actual).containsAll(expected);
    }

    @Test
    public void testFilterMonthly3() {
        AttendanceRepositoryCsv target = createDefaultTarget();
        YearMonth yearMonth = new YearMonth(2019, 4);
        List<Attendance> attendances = ImmutableList.of(
                createAttendance(4, 1, 4, 1),
                createAttendance(4, 2, 4, 2),
                createAttendance(4, 3, 4, 3),
                createAttendance(4, 1, 5, 1),
                createAttendance(4, 2, 5, 2),
                createAttendance(4, 3, 5, 3),
                createAttendance(5, 1, 5, 1),
                createAttendance(5, 2, 5, 2),
                createAttendance(5, 3, 5, 3)
        );
        List<Attendance> expected = ImmutableList.of(
                createAttendance(4, 1, 5, 1),
                createAttendance(4, 2, 5, 2),
                createAttendance(4, 3, 5, 3)
        );

        List<Attendance> actual = target.filterMonthly(attendances, yearMonth);

        assertThat(actual).containsAll(expected);
    }

    @Test
    public void testSerialize() {
        AttendanceRepositoryCsv target = createDefaultTarget();
        Attendance attendance = createAttendance(4, 1, 4, 2);
        String expected = "2019-04-01,09:00,18:00,480,0,2019-04-02 00:00:00.000";

        String actual = target.serialize(attendance);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testDeserialize() {
        AttendanceRepositoryCsv target = createDefaultTarget();
        String line = "2019-04-01,09:00,18:00,480,0,2019-04-02 00:00:00.000";
        Attendance expected = createAttendance(4, 1, 4, 2);

        Attendance actual = target.deserialize(line);

        assertThat(actual).isEqualTo(expected);
    }

    private AttendanceRepositoryCsv createDefaultTarget() {
        CsvSetting setting = new CsvSetting();
        FileIOHelper fileIOHelper = new FileIOHelper();
        return new AttendanceRepositoryCsv(setting, fileIOHelper);
    }

    private Attendance createAttendance(
            int month,
            int day,
            int createMonth,
            int createDay
    ) {
        return Attendance.builder()
                .date(LocalDate.of(2019, month, day))
                .startTime(TimePoint.of(9, 0))
                .endTime(TimePoint.of(18, 0))
                .workMinute(WorkMinute.of(8 * 60))
                .overWorkMinute(WorkMinute.of(0))
                .createAt(LocalDateTime.of(2019, createMonth, createDay, 0, 0))
                .build();
    }
}
