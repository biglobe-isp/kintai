package kintai.datasource;

import kintai.domain.*;
import kintai.service.WorkingDateTotalRecordRepository;
import lombok.RequiredArgsConstructor;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class WorkingDateTotalRecordRepositoryDb implements WorkingDateTotalRecordRepository {
    private final Path csvPath;
    @Override
    public void save(WorkingDateTotalRecord workingDateTotalRecord,LocalDateTime now) {
        File file = new File("data.csv");
        //データを追加するFileWriterオブジェクト作成、ファイルの追記モードでfileを開く。
        try (BufferedWriter bw = Files.newBufferedWriter(csvPath, StandardOpenOption.APPEND)) {
            bw.write(String.format(
                    "%s,%s,%s,%s,%s,%s\n",
                    workingDateTotalRecord.getWorkDay().formatyyyyMMdd(),
                    workingDateTotalRecord.getWorkStart().formatHHmm(),
                    workingDateTotalRecord.getWorkEnd().formatHHmm(),
                    workingDateTotalRecord.getWorkMinutes().getWorkMinutes(),
                    workingDateTotalRecord.getOverWorkMinutes().getOverWorkMinutes(),
                    now
            ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<WorkingDateTotalRecord> findByMonth(YearMonth yearMonth) {
        // String firstMonthValue = workingDateTotalRecordList.get(0).getWorkDay().getValue().substring(0,7);
        //String cavPath = "KintaiData.csv";
        List<WorkingDateTotalRecord> workingDateTotalRecords = new ArrayList<>();
        try (
                BufferedReader br =Files.newBufferedReader(csvPath)
        ) {
            String line = br.readLine();
            while (line != null) {
                String[] columns = line.split(",");
                if (!columns[0].substring(0,7).startsWith(String.valueOf(yearMonth))) {
                    continue;
                }
                workingDateTotalRecords.add(new WorkingDateTotalRecord(
                        WorkDay.parse(columns[0]),
                        WorkStart.parse(columns[1]),
                        WorkEnd.parse(columns[2]),
                        new OverWorkMinutes(Integer.parseInt(columns[3])),
                        new WorkMinutes(Integer.parseInt(columns[4])))
                );
                line = br.readLine();

            }

        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
        return workingDateTotalRecords;
    }
}
