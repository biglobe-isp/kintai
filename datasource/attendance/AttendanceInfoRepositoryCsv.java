package com.naosim.dddwork.datasource.attendance;

import com.naosim.dddwork.domain.attendance.AttendanceInfoRepository;
import com.naosim.dddwork.domain.attendance.input.AttendanceInfoEntity;
import com.naosim.dddwork.domain.attendance.input.WorkDate;
import com.naosim.dddwork.domain.attendance.total.WorkMonth;

import java.util.List;
import java.util.Optional;

public class AttendanceInfoRepositoryCsv implements AttendanceInfoRepository {
    private final AttendanceInfoQueryMapper mapper = new AttendanceInfoQueryMapper();

    public AttendanceInfoEntity regist(AttendanceInfoEntity entity) throws Exception {
        return mapper.regist(entity).create();
    }

    public AttendanceInfoEntity update(AttendanceInfoEntity entity) throws Exception {
        return mapper.update(entity).create();
    }

    public Optional<AttendanceInfoEntity> selectByDate(WorkDate date) throws Exception {
        return Optional.ofNullable(mapper.selectByDate(date))
                .map(entity -> {
                    try {
                        return entity.create();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    public List<AttendanceInfoEntity> selectByMonth(WorkMonth workMonth) throws Exception {
        return mapper.selectByMonth(workMonth).stream().map(
                entity -> {
                    try {
                        return entity.create();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        ).toList();
    }

    public List<AttendanceInfoEntity> selectAll() throws Exception {
        return mapper.selectAll().stream().map(
                entity -> {
                    try {
                        return entity.create();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        ).toList();
    }
}
