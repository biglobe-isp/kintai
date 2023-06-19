package jp.co.biglobe.isp.kintai.datasource;

import jp.co.biglobe.isp.kintai.domain.attendance_record.WorkRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import jp.co.biglobe.isp.kintai.service.workrecord_registration.WorkRecordRepository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class WorkRecordRepositoryCsv implements WorkRecordRepository {
    @Override
    public void persist(WorkRecord workRecord) {
        File file = new File("data.csv");
        try (FileWriter filewriter = new FileWriter(file, true)) {
            filewriter.write(String.format(
                    "%s,%s,%s,%s,%s,%s\n",
                    workRecord.getWorkDate().format(DateTimeFormatter.ofPattern("yyyyMMdd")),
                    workRecord.getWorkTime().getOpeningTime().format(DateTimeFormatter.ofPattern("HHmm")),
                    workRecord.getWorkTime().getClosingTime().format(DateTimeFormatter.ofPattern("HHmm")),
                    workRecord.getWorkRecordMinutes(),
                    workRecord.getOverTimeMinutes(),
                    LocalDateTime.now()
                    // 作成日付 ex. dbならdbで時間取る
            ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
