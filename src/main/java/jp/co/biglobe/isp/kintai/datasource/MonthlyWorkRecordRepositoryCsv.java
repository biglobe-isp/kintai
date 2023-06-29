package jp.co.biglobe.isp.kintai.datasource;

import io.vavr.collection.List;
import jp.co.biglobe.isp.kintai.config.AppProperties;
import jp.co.biglobe.isp.kintai.domain.work_record.WorkRecord;
import jp.co.biglobe.isp.kintai.domain.work_record.WorkTime;
import jp.co.biglobe.isp.kintai.domain.monthly_accumulated_hour.MonthlyWorkRecord;
import jp.co.biglobe.isp.kintai.service.monthly_accumulated_hour.MonthlyWorkRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class MonthlyWorkRecordRepositoryCsv implements MonthlyWorkRecordRepository {
    private final AppProperties appProperties;
    @Override
    public MonthlyWorkRecord refer(String yearMonth) {
        List<WorkRecord> workRecordList = List.empty();
//        File file = new File("data.csv");

        try (
                FileReader fr = new FileReader(appProperties.getWorkRecordCsv());
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
                    workRecordList.append(workRecord);
                }
                line = br.readLine();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new MonthlyWorkRecord(workRecordList);
    }
}
