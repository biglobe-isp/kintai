package jp.co.esumit.kintai.domain.kintai_record

import jp.co.esumit.kintai.domain.kintai_record.registered_time.FixtureRegisteredTime
import jp.co.esumit.kintai.domain.kintai_record.target_day.TargetDay
import jp.co.esumit.kintai.domain.kintai_record.time_card.EndTime
import jp.co.esumit.kintai.domain.kintai_record.time_card.StartTime
import jp.co.esumit.kintai.domain.kintai_record.time_card.TimeCard
import jp.co.esumit.kintai.domain.kintai_record.working_minutes.WorkingMinutes
import jp.co.esumit.kintai.domain.kintai_record.working_minutes.operation_and_working_minutes.BreakTimes
import jp.co.esumit.kintai.domain.kintai_record.working_minutes.overtime_minutes.FixedTime

/**
 * KintaiRecordのFixture
 * 以下項目は実際のロジックを使用
 * + 勤務時間計算
 * + 残業時間計算
 * + 休憩時間
 * + 定時間
 */
class FixtureKintaiRecord {
    static KintaiRecord get(String targetDay, String startTime, String endTime) {
        def target = new TargetDay(targetDay)
        def start = new StartTime(startTime)
        def end = new EndTime(endTime)
        def time = new TimeCard(start, end);
        return new KintaiRecord(target, time, new WorkingMinutes(time, new BreakTimes(), new FixedTime()), FixtureRegisteredTime.create())
    }
}
