package com.naosim.dddwork.datasource;

import com.naosim.dddwork.domain.AttendanceRecord;
import com.naosim.dddwork.domain.AttendanceRecords;
import com.naosim.dddwork.domain.date.Day;
import com.naosim.dddwork.domain.date.Month;
import com.naosim.dddwork.domain.date.WorkingDate;
import com.naosim.dddwork.domain.date.Year;
import com.naosim.dddwork.domain.date.YearMonth;
import com.naosim.dddwork.domain.time.EntryTime;
import com.naosim.dddwork.domain.time.Hour;
import com.naosim.dddwork.domain.time.Minute;
import com.naosim.dddwork.service.AttendanceRecordRepository;
import io.vavr.collection.List;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AttendanceRecordRepositoryCSV implements AttendanceRecordRepository {
    // format - 20190403,0900,1900 <- only working date, start time , end time
    private final String dataFileName = "data.csv";

    public AttendanceRecordRepositoryCSV() {
    }

    public AttendanceRecords load() {
        return load(null);
    }

    public AttendanceRecords load(YearMonth yearMonth) {
        List<AttendanceRecord> records = List.empty();
        File file = new File(dataFileName);

        if (!file.exists()) {
            return new AttendanceRecords(records);
        }

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length != 3) {
                    System.out.println("token size is not 3. wrong format line = " + line);
                    break;
                }
                for (String s : tokens) {
                    if (!isInteger(s)) {
                        System.out.println("token " + s + " is not numeric.");
                        break;
                    }
                }
                if (tokens[0].length() != 8) {
                    System.out.println("Date format error '" + tokens[0] + "'");
                    break;
                }
                Year year = new Year(Integer.parseInt(tokens[0].substring(0, 4)));
                Month month = new Month(Integer.parseInt(tokens[0].substring(4, 6)));
                Day day = new Day(Integer.parseInt(tokens[0].substring(6, 8)));
                WorkingDate workingDate = new WorkingDate(year, month, day);

                EntryTime startTime = parseRecordedTime(tokens[1]);
                EntryTime endTime = parseRecordedTime(tokens[2]);
                AttendanceRecord attendanceRecord = new AttendanceRecord(workingDate, startTime, endTime);
                if (yearMonth == null) {
                    records = records.append(attendanceRecord);
                } else if (yearMonth.getValue() == workingDate.getYearMonth()) {
                    records = records.append(attendanceRecord);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Can't access data file");
        }
        return new AttendanceRecords(records);
    }

    // TODD save -> void ret value
    public void save(AttendanceRecords attendanceRecords) {
        File file = new File(dataFileName);

        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            for (AttendanceRecord attendanceRecord : attendanceRecords.getAttendanceRecords()) {
                bw.write(attendanceRecord.getWorkingDate().toString() + "," +
                                 attendanceRecord.getStartTime().toString() + "," +
                                 attendanceRecord.getEndTime().toString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException ex) {
            throw new RuntimeException("Can't write the record : " + ex, ex);
        }
    }

    public void delete() {

        File file = new File(dataFileName);

        if (file.exists()) {
            boolean ret = file.delete();
            if (!ret) {
                throw new RuntimeException("Can't delete csv file.");
            }
        }
    }

    private EntryTime parseRecordedTime(String token) {
        if (token.length() != 4) {
            System.out.println("Time Format Error = " + token);
            return null;
        }
        Hour hour = new Hour(Integer.parseInt(token.substring(0, 2)));
        Minute minute = new Minute(Integer.parseInt(token.substring(2, 4)));
        return new EntryTime(hour, minute);
    }

    private boolean isInteger(String s) {
        try {
            int i = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
