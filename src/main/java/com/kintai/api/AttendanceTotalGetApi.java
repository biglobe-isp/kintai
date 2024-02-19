package com.kintai.api;

import com.kintai.datasource.repository.CsvAttendanceGetRepository;
import com.kintai.domain.factory.impl.CsvAttendanceFactory;
import com.kintai.domain.service.impl.TotalDomainService;
import com.kintai.service.usecase.impl.AttendanceTotalGetService;
import com.kintai.service.usecase.IAttendanceTotalGetService;
import com.kintai.service.dto.response.ResponseAttendanceTotalGetDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AttendanceTotalGetApi {
    private final IAttendanceTotalGetService iAttendanceTotalGetService;

    public AttendanceTotalGetApi() {
        this.iAttendanceTotalGetService = new AttendanceTotalGetService(new CsvAttendanceGetRepository(new CsvAttendanceFactory()), new TotalDomainService());
    }

    public void getTotal() {
        log.info("勤怠集計処理を開始します。");
        ResponseAttendanceTotalGetDto responseAttendanceTotalGetDto = iAttendanceTotalGetService.get();
        log.info(responseAttendanceTotalGetDto.toString());
        log.info("勤怠集計処理を終了します。");
    }
}
