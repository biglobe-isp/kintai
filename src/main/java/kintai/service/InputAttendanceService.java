package kintai.service;

import kintai.domain.InputAttendance;
import kintai.domain.LaborRegulations;
import kintai.domain.WorkingDateTotalRecord;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InputAttendanceService {
    private final WorkingDateTotalRecordRepository workingDateTotalRecordRepository;
    public void input(InputAttendance inputAttendance){
        // inputAttendanceからworkingDateTotalRecordを算出する
        WorkingDateTotalRecord workingDateTotalRecord = WorkingDateTotalRecord.fromInputAttendance(inputAttendance, LaborRegulations.DEFAULT);
        // 算出したworkingDateTotalRecordを保存する kesu
        workingDateTotalRecordRepository.save(workingDateTotalRecord);
    }

}
