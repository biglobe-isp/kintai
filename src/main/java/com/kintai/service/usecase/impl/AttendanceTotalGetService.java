package com.kintai.service.usecase.impl;

import com.kintai.datasource.entity.Attendance;
import com.kintai.datasource.entity.Total;
import com.kintai.domain.repository.IAttendanceGetRepository;
import com.kintai.domain.service.ITotalDomainService;
import com.kintai.service.usecase.IAttendanceTotalGetService;
import com.kintai.service.dto.response.ResponseAttendanceTotalGetDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class AttendanceTotalGetService implements IAttendanceTotalGetService {
    private final IAttendanceGetRepository iAttendanceGetRepository;

    private final ITotalDomainService iTotalDomainService;

    public AttendanceTotalGetService(IAttendanceGetRepository iAttendanceGetRepository, ITotalDomainService iTotalDomainService) {
        this.iAttendanceGetRepository = iAttendanceGetRepository;
        this.iTotalDomainService = iTotalDomainService;
    }

    @Override
    public ResponseAttendanceTotalGetDto get() {
        String resultMessage = "正常に勤怠データを取得されました。";
        List<Attendance> attendanceList = null;
        try {
            attendanceList = iAttendanceGetRepository.get();
        } catch (Exception e) {
            resultMessage = e.getMessage();
            log.error(e.getStackTrace().toString());
        }

        List<Total> totalList = CollectionUtils.isEmpty(attendanceList) ? new ArrayList<>() : iTotalDomainService.getMonthlyTotalList(attendanceList);

        ResponseAttendanceTotalGetDto responseAttendanceTotalGetDto = new ResponseAttendanceTotalGetDto(totalList, resultMessage);
        return responseAttendanceTotalGetDto;
    }
}
