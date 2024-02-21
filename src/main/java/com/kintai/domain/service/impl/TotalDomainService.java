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

public class TotalDomainService implements ITotalDomainService {
    @Override
    public List<WorkTotal> getMonthlyTotalList(List<Attendance> attendanceList) {
        // 勤怠データを月単位でグルーピング
        Map<String, List<Attendance>> groupAttendanceList = groupingAttendanceList(attendanceList);
        List<WorkTotal> monthlyWorkTotalList = makeUpMonthlyTotalList(groupAttendanceList);
        return monthlyWorkTotalList;
    }

    protected Map<String, List<Attendance>> groupingAttendanceList(List<Attendance> attendanceList) {
        return attendanceList.stream().collect(Collectors.groupingBy(attendance -> attendance.getTotalMonth().getTotalMonth()));
    }

    protected List<WorkTotal> makeUpMonthlyTotalList(Map<String, List<Attendance>> groupAttendanceList) {
        List<WorkTotal> monthlyWorkTotalList = new ArrayList<>();

        // 月単位でグルーピングされたデータを集計する
        groupAttendanceList.forEach((totalMonthValue, valueAttendanceList) -> {
            WorkMinutes workMinutes = new WorkMinutes(valueAttendanceList.stream().map(attendance -> attendance.getWorkMinutes()).collect(Collectors.toList()));
            OverWorkMinutes overWorkMinutes = new OverWorkMinutes(valueAttendanceList.stream().map(attendance -> attendance.getOverWorkMinutes()).collect(Collectors.toList()));
            monthlyWorkTotalList.add(new WorkTotal(new TotalMonth(totalMonthValue), workMinutes, overWorkMinutes));
        });
        return monthlyWorkTotalList;
    }
}
