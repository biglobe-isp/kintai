package kintai.domain;

import java.util.List;

/**
 * 勤怠リポジトリ.
 */
public interface AttendanceRepository {
    /**
     * 登録.
     *
     * @param attendance 勤怠エンティティ
     */
    void persist(Attendance attendance);

    /**
     * 検索.
     * @param yearMonth 対象月
     * @return 勤怠エンティティリスト
     */
    List<Attendance> select(int yearMonth);
}
