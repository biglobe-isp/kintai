package com.naosim.dddwork.datasource;

import com.naosim.dddwork.domain.Attendance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttendanceRecordRepository implements IAttendanceRecordRepository {
    @Override
    public void save(Attendance attendance) {
        CsvDb db = new CsvDb();
        List<String> lineValues = new ArrayList<>();
        lineValues.add(attendance.getYyyymmdd());
        lineValues.add(attendance.getStarthhmm());
        lineValues.add(attendance.getEndhhmm());
        lineValues.add(String.valueOf(attendance.getWorkMinutes()));
        lineValues.add(String.valueOf(attendance.getOrverWorkMinutes()));
        lineValues.add(String.valueOf(attendance.getRule().getRuleId().getId()));
        db.add(lineValues);
    }

    @Override
    public List<Attendance> searchByYearMonth(String yearMonth) {

        CsvDb db = new CsvDb();
        List<List<String>> allDatas = db.read();
        Map<String, Attendance> results = new HashMap<>();

        for (List<String> columns : allDatas) {
            if (!columns.get(0).startsWith(yearMonth)) {
                continue;
            }
            Attendance attendance = new Attendance(
                    columns.get(0),
                    columns.get(1),
                    columns.get(2),
                    Integer.valueOf(columns.get(3)),
                    Integer.valueOf(columns.get(4))
            );
            results.put(attendance.getYyyymmdd(), attendance);
        }

        return new ArrayList<>(results.values());
    }
}
