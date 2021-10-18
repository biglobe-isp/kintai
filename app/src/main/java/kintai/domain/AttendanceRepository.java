package kintai.domain;

import java.time.LocalDate;
import java.util.List;

/**
 * 勤怠リポジトリインターフェース.
 */
public interface AttendanceRepository {
    /**
     * 登録.
     *
     * @param attendance 勤怠エンティティ
     */
    void persist(Attendance attendance);

    /**
     * 更新.
     *
     * @param attendance 勤怠エンティティ
     */
    void update(Attendance attendance);

    /**
     * 勤怠エンティティの存在確認.
     *
     * @param day 出勤日
     */
    boolean exists(LocalDate day);

    /**
     * 検索.
     * @param yearMonth 対象月
     * @return 勤怠エンティティリスト
     */
    List<Attendance> select(int yearMonth);
}
