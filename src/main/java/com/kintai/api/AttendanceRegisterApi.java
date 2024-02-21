package com.kintai.api;

import com.kintai.datasource.repository.CsvAttendanceSaveRepository;
import com.kintai.domain.factory.impl.AttendanceFactory;
import com.kintai.service.usecase.IAttendanceRegisterService;
import com.kintai.service.dto.request.RequestAttendanceRegisterDto;
import com.kintai.service.dto.response.ResponseAttendanceRegisterDto;
import com.kintai.service.usecase.impl.expansion.register.AttendanceRegisterServiceV1;
import lombok.extern.slf4j.Slf4j;

/**
 * 勤怠登録API
 */
@Slf4j
public class AttendanceRegisterApi {
    private final IAttendanceRegisterService iAttendanceRegisterService;

    public AttendanceRegisterApi() {
        this.iAttendanceRegisterService = new AttendanceRegisterServiceV1(new CsvAttendanceSaveRepository(), new AttendanceFactory());
    }

    public void register(String[] args) {
        log.info("勤怠入力処理を開始します。");
        RequestAttendanceRegisterDto requestAttendanceRegisterDto = makeRequestAttendanceRegisterDto(args);
        log.info(requestAttendanceRegisterDto.toString());
        ResponseAttendanceRegisterDto responseAttendanceRegisterDto = iAttendanceRegisterService.register(requestAttendanceRegisterDto);
        log.info(responseAttendanceRegisterDto.toString());
        log.info("勤怠入力処理を終了します。");
    }

    protected RequestAttendanceRegisterDto makeRequestAttendanceRegisterDto(String[] args) {
        return new RequestAttendanceRegisterDto(args[0], args[1], args[2], args[3]);
    }
}
