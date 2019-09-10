package kintai.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class AttendanceMonth {
    /**
     * 内部表現では、LocalDate型の 1として保持する。
     * 例: 2019/04 -> 2019/04/01
     */
    private final LocalDate value;

    public static AttendanceMonth of(int year, int month) {
        return new AttendanceMonth(LocalDate.of(year, month, 1));
    }

    public boolean contains(LocalDate date) {
        return value.isBefore(date) && value.plusMonths(1).isAfter(date);
    }
}
