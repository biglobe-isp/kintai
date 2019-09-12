package kintai.api;

import kintai.domain.AttendanceMonth;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class AttendanceTotalRequest {

    private final String yearMonth;

    public AttendanceMonth getEntity() {
        return AttendanceMonth.of(Integer.valueOf(yearMonth.substring(0, 4)), Integer.valueOf(yearMonth.substring(4)));
    }
}
