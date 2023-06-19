package jp.co.biglobe.isp.kintai.domain.attendance_record;

import org.springframework.stereotype.Component;

import jp.co.biglobe.isp.kintai.domain.work_regulation.WorkRegulation;
import jp.co.biglobe.isp.kintai.domain.input_worktime.InputWorkTime;
@Component
public class WorkRecordFactory {
    public WorkRecord create(InputWorkTime inputWorkTime, WorkRegulation workRegulation) {
        var workDate = inputWorkTime.getWorkDate();
        var workTime = inputWorkTime.getWorkTime();
        var workRecordMinutes = WorkRecordMinutes.from(workTime, workRegulation);
        var overTimeHours  = OverTimeMinutes.from(workRecordMinutes, workRegulation.getRegulatedWorkMinutes());
        return new WorkRecord(workDate, workTime, workRecordMinutes, overTimeHours);
    }
}
