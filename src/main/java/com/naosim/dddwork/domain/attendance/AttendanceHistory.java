package com.naosim.dddwork.domain.attendance;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.YearMonth;

/**
 * 勤怠履歴
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class AttendanceHistory {

    @Getter
    private final AttendanceList attendanceList;

    public TotalWorkMinutesByMonth totalWorkMinutesByMonth(TotalYearMonth totalYearMonth) {

        return new TotalWorkMinutesByMonth(
                this.getAttendanceList().getValue().stream()
                        .filter(attendance ->
                                YearMonth.of(
                                        attendance.getWorkDate().getValue().getYear(),
                                        attendance.getWorkDate().getValue().getMonth()).equals(totalYearMonth.getValue()))
                        .mapToInt(attendance -> attendance.getWorkMinutes().getValue())
                        .sum()
        );
    }

    public TotalOverWorkMinutesByMonth totalOverWorkMinutesByMonth(TotalYearMonth totalYearMonth) {

        return new TotalOverWorkMinutesByMonth(
                this.getAttendanceList().getValue().stream()
                        .filter(attendance ->
                                YearMonth.of(
                                        attendance.getWorkDate().getValue().getYear(),
                                        attendance.getWorkDate().getValue().getMonth()).equals(totalYearMonth.getValue()))
                        .mapToInt(attendance -> attendance.getOverWorkMinutes().getValue())
                        .sum()
        );
    }

    public void print(TotalWorkMinutesByMonth totalWorkMinutesByMonth,
                      TotalOverWorkMinutesByMonth totalOverWorkMinutesByMonth) {

        System.out.println("勤務時間: " +
                totalWorkMinutesByMonth.getValue() / 60 + "時間" +
                totalWorkMinutesByMonth.getValue() % 60 + "分");

        System.out.println("残業時間: " +
                totalOverWorkMinutesByMonth.getValue() / 60 + "時間" +
                totalOverWorkMinutesByMonth.getValue() % 60 + "分");
    }
}
