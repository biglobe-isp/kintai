package com.naosim.dddwork.domain.attendance;

import com.naosim.dddwork.domain.attendance.input.AttendanceInfoEntity;
import com.naosim.dddwork.domain.attendance.input.WorkDate;
import com.naosim.dddwork.domain.attendance.total.WorkMonth;

import java.util.List;
import java.util.Optional;

public interface AttendanceInfoRepository {
    AttendanceInfoEntity regist(AttendanceInfoEntity entity) throws Exception;
    AttendanceInfoEntity update(AttendanceInfoEntity entity) throws Exception;
    Optional<AttendanceInfoEntity> selectByDate(WorkDate workDate) throws Exception;
    List<AttendanceInfoEntity> selectByMonth(WorkMonth workMonth) throws Exception;
    List<AttendanceInfoEntity> selectAll() throws Exception;
}
