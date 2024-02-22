package com.kintai.domain.service.impl;

import com.kintai.datasource.entity.Attendance;
import com.kintai.datasource.entity.WorkTotal;
import com.kintai.datasource.value.OverWorkMinutes;
import com.kintai.datasource.value.TotalMonth;
import com.kintai.datasource.value.WorkMinutes;
import com.kintai.domain.service.ITotalDomainService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 勤怠データを集計するドメインサービスインターフェースを実装したクラス
 */
public class TotalDomainService implements ITotalDomainService {
    /**
     * 月単位で勤怠データを集計します。
     * 月毎の勤怠データを集計するために、勤怠データを集計月でグルーピングします。
     * グルーピングした勤怠データ同士で、労働時間と残業時間を集計し、集計したデータを呼び出し元に返却します。
     * @param attendanceList 集計対象の勤怠リスト
     * @return 月単位で労働時間や残業時間を集計したデータ
     */
    @Override
    public List<WorkTotal> getMonthlyTotalList(List<Attendance> attendanceList) {
        Map<String, List<Attendance>> groupAttendanceList = groupingAttendanceList(attendanceList);
        return  makeUpMonthlyTotalList(groupAttendanceList);
    }

    /**
     * 勤怠データリストを集計月でグルーピングします。
     * @param attendanceList 月単位にグルーピングする前の勤怠データリスト
     * @return 月単位にグルーピングした勤怠データマップ(キー:集計月(yyyyMM)、バリュー：勤怠データリスト)
     */
    protected Map<String, List<Attendance>> groupingAttendanceList(List<Attendance> attendanceList) {
        return attendanceList.stream().collect(Collectors.groupingBy(attendance -> attendance.getTotalMonth().getTotalMonth()));
    }

    /**
     * 集計した勤怠データリストを生成します。
     * 労働時間や残業時間をグルーピングした勤怠データ同士で集計します。
     * @param groupAttendanceList 月単位にグルーピングされた勤怠データリスト
     * @return 労働時間や残業時間を集計した労働集計データリスト
     */
    protected List<WorkTotal> makeUpMonthlyTotalList(Map<String, List<Attendance>> groupAttendanceList) {
        List<WorkTotal> monthlyWorkTotalList = new ArrayList<>();

        // 月単位でグルーピングされたデータを集計する
        groupAttendanceList.forEach((totalMonthValue, valueAttendanceList) -> {
            WorkMinutes workMinutes = new WorkMinutes(valueAttendanceList.stream().map(Attendance::getWorkMinutes).collect(Collectors.toList()));
            OverWorkMinutes overWorkMinutes = new OverWorkMinutes(valueAttendanceList.stream().map(Attendance::getOverWorkMinutes).collect(Collectors.toList()));
            monthlyWorkTotalList.add(new WorkTotal(new TotalMonth(totalMonthValue), workMinutes, overWorkMinutes));
        });
        return monthlyWorkTotalList;
    }
}
