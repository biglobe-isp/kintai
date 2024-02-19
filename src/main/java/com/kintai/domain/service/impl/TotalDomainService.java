package com.kintai.domain.service.impl;

import com.kintai.datasource.entity.Attendance;
import com.kintai.datasource.entity.Total;
import com.kintai.datasource.value.OverWorkMinutes;
import com.kintai.datasource.value.TotalMonth;
import com.kintai.datasource.value.WorkMinutes;
import com.kintai.domain.service.ITotalDomainService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TotalDomainService implements ITotalDomainService {
    @Override
    public List<Total> getMonthlyTotalList(List<Attendance> attendanceList) {
        // 勤怠データを月単位でグルーピング
        Map<String, List<Attendance>> groupAttendanceList = groupingAttendanceList(attendanceList);
        List<Total> monthlyTotalList = makeUpMonthlyTotalList(groupAttendanceList);
        return monthlyTotalList;
    }

    protected Map<String, List<Attendance>> groupingAttendanceList(List<Attendance> attendanceList) {
        return attendanceList.stream().collect(Collectors.groupingBy(attendance -> attendance.getTotalMonth().getTotalMonth()));
    }

    protected List<Total> makeUpMonthlyTotalList(Map<String, List<Attendance>> groupAttendanceList) {
        List<Total> monthlyTotalList = new ArrayList<>();

        // 月単位でグルーピングされたデータを集計する
        groupAttendanceList.forEach((totalMonthValue, valueAttendanceList) -> {
            WorkMinutes workMinutes = new WorkMinutes(valueAttendanceList.stream().map(attendance -> attendance.getWorkMinutes()).collect(Collectors.toList()));
            OverWorkMinutes overWorkMinutes = new OverWorkMinutes(valueAttendanceList.stream().map(attendance -> attendance.getOverWorkMinutes()).collect(Collectors.toList()));
            monthlyTotalList.add(new Total(new TotalMonth(totalMonthValue), workMinutes, overWorkMinutes));
        });
        return monthlyTotalList;
    }
}
