package com.kintai.service.usecase.impl.expansion.register;

import com.kintai.datasource.value.expansion.password.CsvPassword;
import com.kintai.domain.factory.IAttendanceFactory;
import com.kintai.domain.repository.IAttendanceSaveRepository;
import com.kintai.exception.ValidatorException;
import com.kintai.service.dto.request.RequestAttendanceRegisterDto;
import com.kintai.service.dto.response.ResponseAttendanceRegisterDto;
import com.kintai.service.usecase.IAttendanceRegisterService;
import com.kintai.service.usecase.impl.AttendanceRegisterService;

public class AttendanceRegisterServiceV1 extends AttendanceRegisterService implements IAttendanceRegisterService {
    public AttendanceRegisterServiceV1(IAttendanceSaveRepository iAttendanceRepository, IAttendanceFactory iAttendanceFactory) {
        super(iAttendanceRepository, iAttendanceFactory);
    }

    @Override
    public ResponseAttendanceRegisterDto register(RequestAttendanceRegisterDto requestAttendanceRegisterDto) {
        ResponseAttendanceRegisterDto responseAttendanceRegisterDto;
        try {
            authPassword(requestAttendanceRegisterDto.getPassword());
            responseAttendanceRegisterDto = super.register(requestAttendanceRegisterDto);
        } catch (Exception e) {
            resultMessage = e.getMessage();
            responseAttendanceRegisterDto = makeResponseAttendanceRegisterDto(requestAttendanceRegisterDto, resultMessage);
        }
        return responseAttendanceRegisterDto;
    }

    protected void authPassword(String password) throws ValidatorException {
        new CsvPassword(password);
    }
}
