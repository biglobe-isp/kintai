package com.kintai.domain.repository;

import com.kintai.datasource.entity.Attendance;

/**
 * 勤怠リポジトリインターフェース
 */
public interface IAttendanceSaveRepository {
    void save(Attendance attendance) throws Exception;
}
