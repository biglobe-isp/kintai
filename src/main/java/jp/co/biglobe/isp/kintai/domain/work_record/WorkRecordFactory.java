package jp.co.biglobe.isp.kintai.domain.work_record;

import org.springframework.stereotype.Component;

import jp.co.biglobe.isp.kintai.domain.work_regulation.WorkRegulation;
import jp.co.biglobe.isp.kintai.domain.input_worktime.InputWorkTime;
@Component
public class WorkRecordFactory {
    public WorkRecord create(InputWorkTime inputWorkTime, WorkRegulation workRegulation) {
        var workDate = inputWorkTime.getWorkDate();
        var workTime = inputWorkTime.getWorkTime();
        var workRecordMinutes = new WorkRecordMinutes(workTime, workRegulation.getBreakTimeList());
        var overTimeHours  = new OverTimeMinutes(workRecordMinutes.getValue(), workRegulation.getRegulatedWorkMinutes());
        return new WorkRecord(workDate, workTime, workRecordMinutes.getValue(), overTimeHours.getValue());
    }
}
