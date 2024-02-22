package com.kintai.domain.service;

import com.kintai.datasource.entity.Attendance;
import com.kintai.datasource.entity.WorkTotal;

import java.util.List;

/**
 * 勤怠データを集計するドメインサービスインターフェース
 */
public interface ITotalDomainService {
    /**
     * 月毎の勤怠データを集計します。
     * @param attendanceList 集計対象の勤怠リスト
     * @return 集計した勤怠リスト
     */
    List<WorkTotal> getMonthlyTotalList(List<Attendance> attendanceList);
}
