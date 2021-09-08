package com.naosim.dddwork.datasource;

import com.naosim.dddwork.domain.Attendance;
import com.naosim.dddwork.domain.IAttendanceRepository;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class InMemoryAttendanceRepository implements IAttendanceRepository {
    private int rowNumber = 0;
    private Map<String, Attendance> data = new HashMap<>();

    @Override
    public Attendance save(Attendance attendance) {
        this.rowNumber = this.rowNumber + 1;
        data.put(attendance.getYyyymmdd(), attendance);
        return attendance;
    }

    @Override
    public List<Attendance> searchByYearMonth(String yearMonth) {
        List<Attendance> result = new ArrayList<>();
        for (Map.Entry<String, Attendance> entry : data.entrySet()) {
            Attendance attendance = entry.getValue();
            if (attendance.getYyyymmdd().startsWith(yearMonth)) {
                result.add(attendance);
            }
        }
        return result;
    }

    public void printData() {
        for (Map.Entry<String, Attendance> entry : data.entrySet()) {
            Attendance attendance = entry.getValue();
            System.out.println(String.format(
                    "%s %d %d ",
                    attendance.getYyyymmdd(),
                    attendance.getWorkMinutes(),
                    attendance.getOrverWorkMinutes()
            ));
        }
    }

    public void clear() {
        data = new HashMap<>();
    }
}
