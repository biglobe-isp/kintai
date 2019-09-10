package kintai.api;

import kintai.domain.AttendanceMonth;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class AttendanceTotalRequest {

    private final String yearMonth;

    public AttendanceMonth getEntity() {
        return AttendanceMonth.of(Integer.valueOf(yearMonth.substring(0, 4)), Integer.valueOf(yearMonth.substring(4)));
    }
}
