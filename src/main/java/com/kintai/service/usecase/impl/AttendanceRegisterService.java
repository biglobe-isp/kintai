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

import java.text.ParseException;

/**
 * 勤怠登録ユースケース
 */
@Slf4j
public class AttendanceRegisterService implements IAttendanceRegisterService {
    private final IAttendanceSaveRepository iAttendanceRepository;

    private final IAttendanceFactory iAttendanceFactory;

    protected String resultMessage = "正常に勤怠が登録されました。";

    public AttendanceRegisterService(IAttendanceSaveRepository iAttendanceRepository, IAttendanceFactory iAttendanceFactory) {
        this.iAttendanceRepository = iAttendanceRepository;
        this.iAttendanceFactory = iAttendanceFactory;
    }

    @Override
    public ResponseAttendanceRegisterDto register(RequestAttendanceRegisterDto requestAttendanceRegisterDto) {
        try {
            Attendance attendance = makeAttendance(requestAttendanceRegisterDto);
            saveAttendance(attendance);
        } catch (ValidatorException e) {
            resultMessage = e.getMessage();
        } catch (Exception e) {
            resultMessage = e.getMessage();
            log.error(e.getStackTrace().toString());
        }

        return makeResponseAttendanceRegisterDto(requestAttendanceRegisterDto, resultMessage);
    }

    protected void saveAttendance(Attendance attendance) throws Exception {
        iAttendanceRepository.save(attendance);
    }

    protected Attendance makeAttendance(RequestAttendanceRegisterDto requestAttendanceRegisterDto) throws ValidatorException, ParseException {
        return iAttendanceFactory.makeAttendance(
                new AttendanceFactoryDto(
                        requestAttendanceRegisterDto.getWorkDate(),
                        requestAttendanceRegisterDto.getStartTime(),
                        requestAttendanceRegisterDto.getEndTime()
                )
        );
    }

    protected ResponseAttendanceRegisterDto makeResponseAttendanceRegisterDto(RequestAttendanceRegisterDto requestAttendanceRegisterDto, String resultMessage) {
        return new ResponseAttendanceRegisterDto(
                requestAttendanceRegisterDto.getWorkDate(),
                requestAttendanceRegisterDto.getStartTime(),
                requestAttendanceRegisterDto.getEndTime(),
                resultMessage
        );
    }
}
