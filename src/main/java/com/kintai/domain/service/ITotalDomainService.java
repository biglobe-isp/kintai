package com.kintai.domain.service;

import com.kintai.datasource.entity.Attendance;
import com.kintai.datasource.entity.WorkTotal;

import java.util.List;

public interface ITotalDomainService {
    List<WorkTotal> getMonthlyTotalList(List<Attendance> attendanceList);
}
