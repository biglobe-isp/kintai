package com.kintai.datasource.repository;

import com.kintai.datasource.entity.Attendance;
import com.kintai.domain.repository.IAttendanceSaveRepository;

import java.io.File;
import java.io.FileWriter;

/**
 * 勤怠リポジトリインターフェース実装
 */
public class CsvAttendanceSaveRepository implements IAttendanceSaveRepository {
    @Override
    public void save(Attendance attendance) throws Exception {
        saveToFile(attendance);
    }

    protected void saveToFile(Attendance attendance) throws Exception {
        File file = new File("data.csv");
        FileWriter filewriter = new FileWriter(file, true);
        filewriter.write(String.format(
                "%s,%s,%s,%s,%s,%s,%s\n",
                attendance.getTotalMonth().getTotalMonth(),
                attendance.getWorkDate().getWorkDate(),
                attendance.getWorkTime().getStartTime().getStartTime(),
                attendance.getWorkTime().getEndTime().getEndTime(),
                attendance.getWorkMinutes().getWorkMinutes(),
                attendance.getOverWorkMinutes().getOverWorkMinutes(),
                attendance.getCreateLocalDate()
        ));
        filewriter.close();
    }
}
