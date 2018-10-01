package com.naosim.dddwork.domain.attendance;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.YearMonth;

/**
 * 勤怠履歴
 * 2018/09/27 新規作成
 * 2018/09/28 レビュー指摘事項反映 UI関連のメソッドをService層からUI層に移動する対応 print()を削除
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
}
