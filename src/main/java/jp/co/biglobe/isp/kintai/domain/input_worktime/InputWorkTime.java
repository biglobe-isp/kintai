package jp.co.biglobe.isp.kintai.domain.input_worktime;

import jp.co.biglobe.isp.kintai.domain.work_record.WorkTime;
import lombok.Value;

import java.time.LocalDate;

@Value
public class InputWorkTime {
    //WorkTimeofDay とかがいいのでは？
    LocalDate workDate;
    WorkTime workTime;

    public InputWorkTime(LocalDate workDate, WorkTime workTime) {
        this.workDate = workDate;
        this.workTime = workTime;
    }
}
