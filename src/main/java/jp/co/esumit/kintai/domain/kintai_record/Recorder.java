package jp.co.esumit.kintai.domain.kintai_record;

import jp.co.esumit.kintai.domain.kintai_record.registered_time.RegisteredTime;
import jp.co.esumit.kintai.domain.kintai_record.target_day.TargetDay;
import jp.co.esumit.kintai.domain.kintai_record.time_card.EndTime;
import jp.co.esumit.kintai.domain.kintai_record.time_card.StartTime;
import jp.co.esumit.kintai.domain.kintai_record.time_card.TimeCard;
import jp.co.esumit.kintai.domain.kintai_record.working_minutes.WorkingMinutes;
import jp.co.esumit.kintai.domain.kintai_record.working_minutes.operation_and_working_minutes.BreakTimes;
import jp.co.esumit.kintai.domain.kintai_record.working_minutes.overtime_minutes.FixedTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Recorder {
    private final BreakTimes breakTimes;
    private final FixedTime fixedTime;
    private final RegisteredTime registeredTime;

    public KintaiRecord record(TargetDay targetDay, StartTime startTime, EndTime endTime) {

        TimeCard timeCard = new TimeCard(startTime, endTime);
        WorkingMinutes workingMinutes = new WorkingMinutes(timeCard, breakTimes, fixedTime);

        return new KintaiRecord(targetDay, timeCard, workingMinutes, registeredTime);
    }
}
