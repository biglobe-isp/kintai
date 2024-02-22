package com.kintai.domain.repository;

import com.kintai.datasource.entity.Attendance;

import java.util.List;

/**
 * 勤怠データを取得するリポジトリインターフェース
 */
public interface IAttendanceGetRepository {
    /**
     * 勤怠データを取得します
     * @return 勤怠データリスト
     * @throws Exception 取得時に想定外を検知した場合、{@link Exception}をスローします。
     */
    List<Attendance> get() throws Exception;
}
