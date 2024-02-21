package com.kintai.api;

import com.kintai.datasource.repository.CsvAttendanceGetRepository;
import com.kintai.domain.factory.impl.CsvAttendanceFactory;
import com.kintai.domain.service.impl.TotalDomainService;
import com.kintai.service.dto.request.RequestAttendanceGetDto;
import com.kintai.service.dto.request.RequestAttendanceRegisterDto;
import com.kintai.service.usecase.IAttendanceTotalGetService;
import com.kintai.service.dto.response.ResponseAttendanceTotalGetDto;
import com.kintai.service.usecase.impl.AttendanceTotalGetService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AttendanceTotalGetApi {
    private final IAttendanceTotalGetService iAttendanceTotalGetService;

    public AttendanceTotalGetApi() {
        this.iAttendanceTotalGetService = new AttendanceTotalGetService(new CsvAttendanceGetRepository(new CsvAttendanceFactory()), new TotalDomainService());
    }

    public void getTotal(String[] args) {
        log.info("勤怠集計処理を開始します。");
        RequestAttendanceGetDto requestAttendanceGetDto = makeRequestAttendanceGetDto(args);
        ResponseAttendanceTotalGetDto responseAttendanceTotalGetDto = iAttendanceTotalGetService.get(requestAttendanceGetDto);
        log.info(responseAttendanceTotalGetDto.toString());
        log.info("勤怠集計処理を終了します。");
    }

    protected RequestAttendanceGetDto makeRequestAttendanceGetDto(String[] args) {
        return new RequestAttendanceGetDto(args[0]);
    }
}
