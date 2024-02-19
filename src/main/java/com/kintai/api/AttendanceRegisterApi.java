package com.kintai.api;

import com.kintai.datasource.repository.CsvAttendanceSaveRepository;
import com.kintai.domain.factory.impl.AttendanceFactory;
import com.kintai.service.usecase.impl.AttendanceRegisterService;
import com.kintai.service.usecase.IAttendanceRegisterService;
import com.kintai.service.dto.request.RequestAttendanceRegisterDto;
import com.kintai.service.dto.response.ResponseAttendanceRegisterDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AttendanceRegisterApi {
    private final IAttendanceRegisterService iAttendanceRegisterService;

    public AttendanceRegisterApi() {
        this.iAttendanceRegisterService = new AttendanceRegisterService(new CsvAttendanceSaveRepository(), new AttendanceFactory());
    }

    public void register(String[] args) {
        log.info("勤怠入力処理を開始します。");
        RequestAttendanceRegisterDto requestAttendanceRegisterDto = makeRequestAttendanceRegisterDto(args);
        log.info(requestAttendanceRegisterDto.toString());
        ResponseAttendanceRegisterDto responseAttendanceRegisterDto = iAttendanceRegisterService.register(requestAttendanceRegisterDto);
        log.info(responseAttendanceRegisterDto.toString());
        log.info("勤怠入力処理を終了します。");
    }

    private RequestAttendanceRegisterDto makeRequestAttendanceRegisterDto(String[] args) {
        return new RequestAttendanceRegisterDto(args[0], args[1], args[2]);
    }
}
