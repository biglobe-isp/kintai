package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.param_object.LoadAttendanceInfo;
import com.naosim.dddwork.domain.param_object.NewAttendanceInfo;
import com.naosim.dddwork.domain.param_object.UpdateAttendanceInfo;
import com.naosim.dddwork.domain.value_object.Date;
import com.naosim.dddwork.domain.value_object.Time;
import com.naosim.dddwork.domain.value_object.WorkTimeMinutes;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class AttendanceInfo {
    private Date _date;
    private Time _startTime;
    private Time _endTime;
    private WorkTimeMinutes _workTime;
    private WorkTimeMinutes _overWorkTime;

    private static final LocalTime WORK_START_TIME = LocalTime.of(9,0);
    private static final LocalTime WORK_END_TIME = LocalTime.of(18, 0);
    private static final Integer MAX_DAILY_WORK_MINUTES = 480;
    private static final HashMap<LocalTime, LocalTime> REST_TIME_MAP = new HashMap<LocalTime, LocalTime>(){
        {
            put(LocalTime.of(12,0), LocalTime.of(13,0));
            put(LocalTime.of(18,0), LocalTime.of(19,0));
            put(LocalTime.of(21,0), LocalTime.of(22,0));
        }
    };


    public AttendanceInfo(NewAttendanceInfo attendanceInfo) throws Exception {
        if(attendanceInfo.getStartTime().getValue().isAfter(WORK_START_TIME)){
            throw new Exception("遅刻はNGです。");
        }

        _date = attendanceInfo.getDate();
        _startTime = attendanceInfo.getStartTime();
        _endTime = attendanceInfo.getEndTime();
        calculateWorkTimeAndOverWorkTime();
    }

    public AttendanceInfo(LoadAttendanceInfo attendanceInfo) {
        _date = attendanceInfo.getDate();
        _startTime = attendanceInfo.getStartTime();
        _endTime = attendanceInfo.getEndTime();
        _workTime = attendanceInfo.getWorkTime();
        _overWorkTime = attendanceInfo.getOverWorkTime();
    }

    public void update(UpdateAttendanceInfo attendanceInfo) throws Exception {
        if(attendanceInfo.getStartTime().getValue().isAfter(WORK_START_TIME)){
            throw new Exception("遅刻はNGです。");
        }

        _startTime = attendanceInfo.getStartTime();
        _endTime = attendanceInfo.getEndTime();
        calculateWorkTimeAndOverWorkTime();
    }

    public Date getDate() {return _date;}
    public Time getStartTime() {return _startTime;}
    public Time getEndTime() {return _endTime;}
    public WorkTimeMinutes getWorkTime() {return _workTime;}
    public WorkTimeMinutes getOverWorkTime() {return _overWorkTime;}

    private void calculateWorkTimeAndOverWorkTime() {
        Long workTime = ChronoUnit.MINUTES.between(getStartTime().getValue(), getEndTime().getValue());

        //休憩時間を引く
        for(LocalTime s : REST_TIME_MAP.keySet()){
            LocalTime e = REST_TIME_MAP.get(s);
            if(getEndTime().getValue().isAfter(s) && getStartTime().getValue().isBefore(e)){
                workTime -= ChronoUnit.MINUTES.between(
                        getStartTime().getValue().isAfter(s) ? getStartTime().getValue() : s,
                        getEndTime().getValue().isBefore(e) ? getEndTime().getValue() : e
                );
            }
        }

        _workTime = new WorkTimeMinutes(workTime.intValue());
        _overWorkTime = new WorkTimeMinutes(workTime > MAX_DAILY_WORK_MINUTES ? workTime.intValue() - MAX_DAILY_WORK_MINUTES : 0);
    }


}
