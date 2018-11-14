package refactor.domain.dto;

import refactor.domain.dto.Item.DateItem;
import refactor.domain.dto.Item.EndTimeItem;
import refactor.domain.dto.Item.StartTimeItem;
import refactor.domain.dto.Item.WorkAndOverWorkMinutesItem;

public class AttendanceData {
    private final DateItem date;
    private final StartTimeItem startTime;
    private final EndTimeItem endTime;
    private final WorkAndOverWorkMinutesItem workAndOverWorkMinutesItem;
    private final String nowTime;

    public AttendanceData(DateItem date, StartTimeItem startTime, EndTimeItem endTime,WorkAndOverWorkMinutesItem workAndOverWorkMinutesItem, String nowTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.workAndOverWorkMinutesItem =workAndOverWorkMinutesItem;
        this.nowTime = nowTime;
    }

    public DateItem getDate() {
        return date;
    }

    public StartTimeItem getStartTime() {
        return startTime;
    }

    public EndTimeItem getEndTime() {
        return endTime;
    }

    public WorkAndOverWorkMinutesItem getWorkAndOverWorkMinutesItem() {
        return workAndOverWorkMinutesItem;
    }

    public String getNowTime() {
        return nowTime;
    }

    public String valueOfStartTimeString(){
        return startTime.valueOfHHMM();
    }

    public String valueOfEndTimeString(){
        return endTime.valueOfHHMM();
    }

}
