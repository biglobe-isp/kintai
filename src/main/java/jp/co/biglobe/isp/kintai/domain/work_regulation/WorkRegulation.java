package jp.co.biglobe.isp.kintai.domain.work_regulation;

import lombok.Value;

import java.util.List;

/**
 * 就業規則
 */
@Value
public class WorkRegulation {
    RegulatedWorkTime regulatedWorkTime;
    List<BreakTime> breakTimeList;

    public WorkRegulation (RegulatedWorkTime regulatedWorkTime, List<BreakTime> breakTimeList) {
        this.regulatedWorkTime = regulatedWorkTime;
        this.breakTimeList= breakTimeList;
    }

    /**
     * getRegulatedWorkMinutes
     * @return int
     */
    public int getRegulatedWorkMinutes() {
        var restMinutes = 0;
        var regulatedOpeningHours = this.regulatedWorkTime.getRegulatedOpeningTime().getHour();
        var regulatedOpeningMinutes = regulatedOpeningHours * 60 + this.regulatedWorkTime.getRegulatedOpeningTime().getMinute();
        var regulatedClosingHours = this.regulatedWorkTime.getRegulatedClosingTime().getHour();
        var regulatedClosingMinutes = regulatedClosingHours * 60 + this.regulatedWorkTime.getRegulatedClosingTime().getMinute();

        for(BreakTime breakTime: breakTimeList) {
            var breakEndHours = breakTime.getBreakEndTime().getHour();
            if(regulatedClosingHours >= breakEndHours) restMinutes += 60;
        }
        return regulatedClosingMinutes - regulatedOpeningMinutes - restMinutes;
    }
}
