package com.naosim.dddwork.service.attendance;

import com.naosim.dddwork.domain.attendance.AttendanceRepository;
import com.naosim.dddwork.domain.use_case.AttendanceTotalInquiryResult;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 勤怠管理集計Service
 */
@Service
//@RequiredArgsConstructor
@NoArgsConstructor
public class AttendanceTotalService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    //    public AttendanceTotalInquiryResult input(AttendanceTotalInquiry attendanceTotalInquiry) {
    public AttendanceTotalInquiryResult refer(String[] args) {

        //TODO 各種チェック

        //TODO 集計

        //TODO 結果の返却

        String yearMonth = args[1];

        int totalWorkMinutes = 0;
        int totalOverWorkMinutes = 0;

        File file = new File("data.csv");

        try (
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
        ) {

            String line = br.readLine();
            Map<String, Integer> totalWorkMinutesMap = new HashMap<>();
            Map<String, Integer> totalOverWorkMinutesMap = new HashMap<>();
            while (line != null) {
                String[] columns = line.split(",");
                if (!columns[0].startsWith(yearMonth)) {
                    continue;
                }
                totalWorkMinutesMap.put(columns[0], Integer.valueOf(columns[3]));
                totalOverWorkMinutesMap.put(columns[0], Integer.valueOf(columns[4]));

                line = br.readLine();
            }

            Set<String> keySet = totalWorkMinutesMap.keySet();
            for (String key : keySet) {
                totalWorkMinutes += totalWorkMinutesMap.get(key);
                totalOverWorkMinutes += totalOverWorkMinutesMap.get(key);
            }

            System.out.println("勤務時間: " + totalWorkMinutes / 60 + "時間" + totalWorkMinutes % 60 + "分");
            System.out.println("残業時間: " + totalOverWorkMinutes / 60 + "時間" + totalOverWorkMinutes % 60 + "分");
        } catch (Exception ex) {
            //TODO 暫定
            return null;
        }

        return null;
    }
}
