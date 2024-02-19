package com.kintai.service.usecase.impl;

import com.kintai.datasource.entity.Attendance;
import com.kintai.domain.dto.AttendanceFactoryDto;
import com.kintai.domain.factory.IAttendanceFactory;
import com.kintai.domain.repository.IAttendanceSaveRepository;
import com.kintai.exception.ValidatorException;
import com.kintai.service.usecase.IAttendanceRegisterService;
import com.kintai.service.dto.request.RequestAttendanceRegisterDto;
import com.kintai.service.dto.response.ResponseAttendanceRegisterDto;
import lombok.extern.slf4j.Slf4j;

/**
 * 勤怠登録ユースケース
 */
@Slf4j
public class AttendanceRegisterService implements IAttendanceRegisterService {
    private final IAttendanceSaveRepository iAttendanceRepository;

    private final IAttendanceFactory iAttendanceFactory;

    private String resultMessage = "正常に勤怠が登録されました。";

    public AttendanceRegisterService(IAttendanceSaveRepository iAttendanceRepository, IAttendanceFactory iAttendanceFactory) {
        this.iAttendanceRepository = iAttendanceRepository;
        this.iAttendanceFactory = iAttendanceFactory;
    }

    @Override
    public ResponseAttendanceRegisterDto register(RequestAttendanceRegisterDto requestAttendanceRegisterDto) {
        try {
            Attendance attendance = iAttendanceFactory.makeAttendance(new AttendanceFactoryDto(requestAttendanceRegisterDto.getWorkDate(),requestAttendanceRegisterDto.getStartTime(), requestAttendanceRegisterDto.getEndTime()));
            iAttendanceRepository.save(attendance);
        } catch (ValidatorException e) {
            resultMessage = e.getMessage();
        } catch (Exception e) {
            resultMessage = e.getMessage();
            log.error(e.getStackTrace().toString());
        }

        return new ResponseAttendanceRegisterDto(
                requestAttendanceRegisterDto.getWorkDate(),
                requestAttendanceRegisterDto.getStartTime(),
                requestAttendanceRegisterDto.getEndTime(),
                resultMessage
        );
    }
}
