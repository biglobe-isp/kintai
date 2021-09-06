package jp.co.esumit.kintai.domain.kintai_record;

import jp.co.esumit.kintai.domain.kintai_record.registered_time.RegisteredTime;
import jp.co.esumit.kintai.domain.kintai_record.target_day.TargetDay;
import jp.co.esumit.kintai.domain.kintai_record.time_card.TimeCard;
import jp.co.esumit.kintai.domain.kintai_record.working_minutes.WorkingMinutes;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class KintaiRecord {//業務的にナニコレ　Infoは使わないほうがいいワード
    TargetDay targetDay;
    TimeCard stampingTimes;
    WorkingMinutes workingMinutes;
    RegisteredTime registeredTime;

    public int getActualWorkingMinutesValue() {

        return workingMinutes.getActualWorkingMinutes().getValue();
    }

    public int getOvertimeValue() {

        return workingMinutes.getOvertimeMinutes().getValue();
    }
}