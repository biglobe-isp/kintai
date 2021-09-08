package com.naosim.dddwork.datasource;

import com.naosim.dddwork.domain.Attendance;
import com.naosim.dddwork.domain.IAttendanceRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvAttendanceRepository implements IAttendanceRepository {
    private CsvDb db;

    public CsvAttendanceRepository() {
        this.db = new CsvDb("attendance");
    }

    @Override

    public Attendance save(Attendance attendance) {
        List<String> lineValues = new ArrayList<>();
        lineValues.add(attendance.getYyyymmdd());
        lineValues.add(attendance.getStarthhmm());
        lineValues.add(attendance.getEndhhmm());
        lineValues.add(String.valueOf(attendance.getWorkMinutes()));
        lineValues.add(String.valueOf(attendance.getOrverWorkMinutes()));
        db.add(lineValues);
        return attendance;
    }

    @Override
    public List<Attendance> searchByYearMonth(String yearMonth) {

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
