package kintai.domain;

import java.time.YearMonth;
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
     * 検索.
     * @param yearMonth 対象月
     * @return 勤怠エンティティリスト
     */
    List<Attendance> select(YearMonth yearMonth);
}
