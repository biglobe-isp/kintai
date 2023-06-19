package jp.co.biglobe.isp.kintai.datasource;

import jp.co.biglobe.isp.kintai.domain.attendance_record.WorkRecord;
import jp.co.biglobe.isp.kintai.domain.attendance_record.WorkTime;
import jp.co.biglobe.isp.kintai.domain.monthly_accumulated_hour.MonthlyWorkRecord;
import jp.co.biglobe.isp.kintai.service.monthly_accumulated_hour.MonthlyWorkRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class MonthlyWorkRecordRepositoryCsv implements MonthlyWorkRecordRepository {
    @Override
    public MonthlyWorkRecord refer(String yearMonth) {
//        List<WorkRecord> workRecordList = new ArrayList<>();
        Map<String, WorkRecord> workRecordMap = new HashMap<>();
        File file = new File("data.csv");

        try (
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr)
        ) {
            String line = br.readLine();

            while (line != null) {
                String[] columns = line.split(",");
                if(columns[0].startsWith(yearMonth)) {
                    var workRecord = new WorkRecord(LocalDate.parse(columns[0], DateTimeFormatter.ofPattern("yyyyMMdd")),
                                     new WorkTime(LocalTime.parse(columns[1],DateTimeFormatter.ofPattern("HHmm")), LocalTime.parse(columns[2], DateTimeFormatter.ofPattern("HHmm"))),
                                     Integer.parseInt(columns[3]),
                                     Integer.parseInt(columns[4])
                    );
//                    workRecordList.add(workRecord);
                    workRecordMap.put(columns[0], workRecord);
                }
                line = br.readLine();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        return new MonthlyWorkRecord(workRecordList);
        return new MonthlyWorkRecord(workRecordMap);
    }
}
