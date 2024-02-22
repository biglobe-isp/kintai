package com.kintai.domain.repository;

import com.kintai.datasource.entity.Attendance;

/**
 * 勤怠データを登録するリポジトリインターフェース
 */
public interface IAttendanceSaveRepository {
    /**
     * 勤怠データを保存するインターフェース
     * @param attendance 保存対象の勤怠エンティティ
     * @throws Exception 保存時に想定外を検知した場合、{@link Exception}をスローします。
     */
    void save(Attendance attendance) throws Exception;
}
