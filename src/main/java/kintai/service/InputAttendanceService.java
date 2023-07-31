package kintai.service;

import kintai.domain.InputAttendance;
import kintai.domain.WorkingDateTotalRecord;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InputAttendanceService {
    private final WorkingDateTotalRecordRepository workingDateTotalRecordRepository;
    public void input(InputAttendance inputAttendance){
        // inputAttendanceからworkingDateTotalRecordを算出する。
        // WorkingDataTotalRecord workingDateTotalRecord = kintai.keisan;
        WorkingDateTotalRecord workingDateTotalRecord = null;
        // 算出したworkingDateTotalRecordを保存する kesu
        workingDateTotalRecordRepository.save(workingDateTotalRecord);
    }
}
