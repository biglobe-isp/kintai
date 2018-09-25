package com.naosim.dddwork.service.attendance;

import com.naosim.dddwork.domain.attendance.AttendanceRepository;
import com.naosim.dddwork.domain.use_case.AttendanceInputApplicationResult;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;

/**
 * 勤怠管理入力Service
 */
@Service
@NoArgsConstructor
public class AttendanceInputService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    //    public AttendanceInputApplicationResult input(AttendanceInputApplication attendanceInputApplication) {
    public AttendanceInputApplicationResult input(String[] args) {

        //TODO 各種チェック

        //TODO 永続化

        //TODO 結果の返却

        String date = args[1];
        String start = args[2];
        String end = args[3];

        int workMinutes = getWorkMinutes(start, end);

        int overWorkMinutes = getOverWorkMinutes(workMinutes);

        File file = new File("data.csv");

        try (FileWriter filewriter = new FileWriter(file, true)) {

            filewriter.write(String.format("%s,%s,%s,%s,%s,%s\n", date, start, end, workMinutes, overWorkMinutes, LocalDateTime.now().toString()));

            System.out.println(String.format("write: %s,%s,%s,%s,%s,%s", date, start, end, workMinutes, overWorkMinutes, LocalDateTime.now().toString()));

        } catch (Exception ex) {
            //TODO 暫定
            return null;
        }

        return null;
    }

    private static int getWorkMinutes(String start, String end) {

        int startH = Integer.valueOf(start.substring(0, 2));
        int startM = Integer.valueOf(start.substring(2, 4));

        int endH = Integer.valueOf(end.substring(0, 2));
        int endM = Integer.valueOf(end.substring(2, 4));

        int workMinutes = endH * 60 + endM - (startH * 60 + startM);

        if (endH == 12) {
            workMinutes -= endM;
        } else if (endH >= 13) {
            workMinutes -= 60;
        }

        if (endH == 18) {
            workMinutes -= endM;
        } else if (endH >= 19) {
            workMinutes -= 60;
        }

        if (endH == 21) {
            workMinutes -= endM;
        } else if (endH >= 22) {
            workMinutes -= 60;
        }

        return workMinutes;
    }

    private static int getOverWorkMinutes(int workMinutes) {

        return Math.max(workMinutes - 8 * 60, 0);
    }
}
