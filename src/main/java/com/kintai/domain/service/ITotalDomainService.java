package com.kintai.domain.service;

import com.kintai.datasource.entity.Attendance;
import com.kintai.datasource.entity.Total;

import java.util.List;

public interface ITotalDomainService {
    List<Total> getMonthlyTotalList(List<Attendance> attendanceList);
}
