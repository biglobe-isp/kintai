package com.naosim.dddwork.service;

import com.naosim.dddwork.datasource.WorkTimeDAO;
import com.naosim.dddwork.datasource.WorkTimeEntity;
import com.naosim.dddwork.domain.*;
import com.naosim.dddwork.domain.param_object.LoadAttendanceInfo;
import com.naosim.dddwork.domain.param_object.NewAttendanceInfo;
import com.naosim.dddwork.domain.param_object.UpdateAttendanceInfo;
import com.naosim.dddwork.domain.value_object.Date;
import com.naosim.dddwork.domain.value_object.Time;
import com.naosim.dddwork.domain.value_object.WorkTimeMinutes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class InputService {
    private final WorkTimeDAO dao = new WorkTimeDAO();
    public void input(LocalDate date, LocalTime startTime, LocalTime endTime) throws Exception {
        WorkTimeEntity entity = dao.selectByDate(date);
        if(entity == null){
            AttendanceInfo attendanceInfo = new AttendanceInfo(new NewAttendanceInfo(new Date(date), new Time(startTime), new Time(endTime)));

            entity = new WorkTimeEntity(attendanceInfo.getDate().getValue()
                    , attendanceInfo.getStartTime().getValue()
                    , attendanceInfo.getEndTime().getValue()
                    , attendanceInfo.getWorkTime().getValue()
                    , attendanceInfo.getOverWorkTime().getValue()
                    , LocalDateTime.now());
            dao.regist(entity);
        }
        else {
            AttendanceInfo attendanceInfo = new AttendanceInfo(new LoadAttendanceInfo(
                    new Date(entity.date)
                    , new Time(entity.startTime)
                    , new Time(entity.endTime)
                    , new WorkTimeMinutes(entity.workMinutes)
                    , new WorkTimeMinutes(entity.overWorkMinutes)));

            attendanceInfo.update(new UpdateAttendanceInfo(new Time(startTime), new Time(endTime)));
            entity.startTime = attendanceInfo.getStartTime().getValue();
            entity.endTime = attendanceInfo.getEndTime().getValue();
            entity.workMinutes = attendanceInfo.getWorkTime().getValue();
            entity.overWorkMinutes = attendanceInfo.getOverWorkTime().getValue();
            dao.update(entity);
        }
    }
}
