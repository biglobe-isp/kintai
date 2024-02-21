package com.kintai.service.usecase.impl;

import com.kintai.datasource.entity.Attendance;
import com.kintai.datasource.entity.WorkTotal;
import com.kintai.datasource.value.expansion.password.CsvPassword;
import com.kintai.domain.repository.IAttendanceGetRepository;
import com.kintai.domain.service.ITotalDomainService;
import com.kintai.exception.ValidatorException;
import com.kintai.service.dto.request.RequestAttendanceGetDto;
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

    private String resultMessage = "正常に勤怠データを取得されました。";

    public AttendanceTotalGetService(IAttendanceGetRepository iAttendanceGetRepository, ITotalDomainService iTotalDomainService) {
        this.iAttendanceGetRepository = iAttendanceGetRepository;
        this.iTotalDomainService = iTotalDomainService;
    }

    @Override
    public ResponseAttendanceTotalGetDto get() {
        List<Attendance> attendanceList = null;
        try {
            attendanceList = getAttendanceList();
        } catch (Exception e) {
            resultMessage = e.getMessage();
            log.error(e.getStackTrace().toString());
        }

        List<WorkTotal> workTotalList = summaryMonthlyTotal(attendanceList);
        return makeResponseAttendanceTotalGetDto(workTotalList, resultMessage);
    }

    @Override
    public ResponseAttendanceTotalGetDto get(RequestAttendanceGetDto requestAttendanceGetDto) {
        ResponseAttendanceTotalGetDto responseAttendanceTotalGetDto = null;
        try {
            new CsvPassword(requestAttendanceGetDto.getPassword());
            responseAttendanceTotalGetDto = get();
        } catch (ValidatorException e) {
            resultMessage = e.getMessage();
            responseAttendanceTotalGetDto = makeResponseAttendanceTotalGetDto(summaryMonthlyTotal(null), resultMessage);
        }
        return responseAttendanceTotalGetDto;
    }

    protected List<Attendance> getAttendanceList() throws Exception {
        return iAttendanceGetRepository.get();
    }

    protected List<WorkTotal> summaryMonthlyTotal(List<Attendance> attendanceList) {
        if (CollectionUtils.isEmpty(attendanceList)) {
            return new ArrayList<>();
        }
        return iTotalDomainService.getMonthlyTotalList(attendanceList);
    }

    protected ResponseAttendanceTotalGetDto makeResponseAttendanceTotalGetDto(List<WorkTotal> workTotalList, String resultMessage) {
        return new ResponseAttendanceTotalGetDto(workTotalList, resultMessage);
    }
}
