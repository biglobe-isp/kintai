package jp.co.esumit.kintai.domain.kintai_info;

import jp.co.esumit.kintai.domain.kintai_info.end_time.EndTime;
import jp.co.esumit.kintai.domain.kintai_info.office_minutes.OfficeMinutes;
import jp.co.esumit.kintai.domain.kintai_info.office_minutes.OfficeMinutesCalculator;
import jp.co.esumit.kintai.domain.kintai_info.overtime_minutes.OvertimeMinutes;
import jp.co.esumit.kintai.domain.kintai_info.overtime_minutes.OvertimeMinutesCalculator;
import jp.co.esumit.kintai.domain.kintai_info.registered_time.RegisteredTime;
import jp.co.esumit.kintai.domain.kintai_info.start_time.StartTime;
import jp.co.esumit.kintai.domain.kintai_info.target_day.TargetDay;
import lombok.RequiredArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@RequiredArgsConstructor
public class KintaiInfoMaker {
    private final OfficeMinutesCalculator officeMinutesCalculator;
    private final OvertimeMinutesCalculator overtimeMinutesCalculator;

    public KintaiInfo create(TargetDay targetDay, StartTime startTime, EndTime endTime) {

        OfficeMinutes officeMinutes = officeMinutesCalculator.calc(startTime, endTime);
        OvertimeMinutes overtimeMinutes = overtimeMinutesCalculator.calc(officeMinutes);
        RegisteredTime registeredTime = this.getRegisteredTime();

        return new KintaiInfo(
                targetDay,
                startTime,
                endTime,
                officeMinutes,
                overtimeMinutes,
                registeredTime
        );
    }

    private RegisteredTime getRegisteredTime() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String now = format.format(date);
        return new RegisteredTime(now);
    }
}
