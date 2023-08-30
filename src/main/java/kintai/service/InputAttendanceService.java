package kintai.service;

import kintai.domain.InputAttendance.InputAttendance;
import kintai.domain.LaborRegulations.LaborRegulations;
import kintai.domain.WorkingDateTotalRecord.WorkingDateTotalRecord;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class InputAttendanceService {
    private final WorkingDateTotalRecordRepository workingDateTotalRecordRepository;
    public void input(InputAttendance inputAttendance,LocalDateTime now){
        // inputAttendanceからworkingDateTotalRecordを算出する
        WorkingDateTotalRecord workingDateTotalRecord = WorkingDateTotalRecord.fromInputAttendance(inputAttendance, LaborRegulations.DEFAULT);
        // 算出したworkingDateTotalRecordを保存する kesu
        workingDateTotalRecordRepository.save(workingDateTotalRecord, now);
    }

}
