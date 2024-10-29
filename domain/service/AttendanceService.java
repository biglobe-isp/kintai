package com.naosim.dddwork.domain.service;

import com.naosim.dddwork.datasource.WorkTimeCSV;
import com.naosim.dddwork.datasource.WorkTimeEntity;
import com.naosim.dddwork.datasource.WorkTimeRepository;
import com.naosim.dddwork.domain.dto.NewAttendanceInfoDTO;
import com.naosim.dddwork.domain.entity.AttendanceInfo;
import com.naosim.dddwork.domain.value_object.AttendanceTotalData;
import com.naosim.dddwork.domain.value_object.Date;
import com.naosim.dddwork.domain.value_object.WorkEndTime;
import com.naosim.dddwork.domain.value_object.WorkStartTime;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class AttendanceService {
    private final WorkTimeRepository repository = new WorkTimeCSV();

    public void input(Date date, WorkStartTime workStartTime, WorkEndTime workEndTime) throws Exception {
        WorkTimeEntity entity = repository.selectByDate(date.getValue());
        AttendanceInfo attendanceInfo = new AttendanceInfo(new NewAttendanceInfoDTO(date, workStartTime, workEndTime));

        //新規登録
        if (entity == null) {
            entity = new WorkTimeEntity(attendanceInfo.getDate().getValue()
                    , attendanceInfo.getWorkStartTime().getValue()
                    , attendanceInfo.getWorkEndTime().getValue()
                    , attendanceInfo.getWorkTime().getValue()
                    , attendanceInfo.getOverWorkTime().getValue()
                    , LocalDateTime.now());
            repository.regist(entity);
        }
        //更新
        else {
            entity.startTime = attendanceInfo.getWorkStartTime().getValue();
            entity.endTime = attendanceInfo.getWorkEndTime().getValue();
            entity.workMinutes = attendanceInfo.getWorkTime().getValue();
            entity.overWorkMinutes = attendanceInfo.getOverWorkTime().getValue();
            repository.update(entity);
        }
    }

    public AttendanceTotalData getMonthlyTotal() throws Exception {
        ArrayList<WorkTimeEntity> entityList = repository.selectAll();

        return new AttendanceTotalData(
                entityList.stream().mapToInt(e -> e.workMinutes).sum(),
                entityList.stream().mapToInt(e -> e.overWorkMinutes).sum()
        );
    }
}
