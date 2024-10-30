package com.naosim.dddwork.domain.attendance;

import com.naosim.dddwork.datasource.attendance.AttendanceInfoRepositoryCsv;
import com.naosim.dddwork.domain.attendance.input.AttendanceInfoEntity;
import com.naosim.dddwork.domain.attendance.input.WorkDate;
import com.naosim.dddwork.domain.attendance.input.WorkEndTime;
import com.naosim.dddwork.domain.attendance.input.WorkStartTime;
import com.naosim.dddwork.domain.attendance.total.AttendanceTotalData;

import java.util.List;
import java.util.Optional;

public class AttendanceService {
    private final AttendanceInfoRepository repository = new AttendanceInfoRepositoryCsv();

    public void input(WorkDate date, WorkStartTime workStartTime, WorkEndTime workEndTime) throws Exception {
        Optional<AttendanceInfoEntity> attendanceInfoEntityOptional = repository.selectByDate(date);

        //新規登録
        if (attendanceInfoEntityOptional.isEmpty()) {
            repository.regist(new AttendanceInfoEntity(date, workStartTime, workEndTime));
        }
        //更新
        else {
            repository.update(attendanceInfoEntityOptional.get()
                                      .updateWorkStartTimeAndWorkEndTime(workStartTime, workEndTime));
        }
    }

    public AttendanceTotalData getMonthlyTotal() throws Exception {
        List<AttendanceInfoEntity> entityList = repository.selectAll();

        return new AttendanceTotalData(
                entityList.stream().mapToInt(e -> e.getWorkTime().getValue()).sum(),
                entityList.stream().mapToInt(e -> e.getOverWorkTime().getValue()).sum()
        );
    }
}
