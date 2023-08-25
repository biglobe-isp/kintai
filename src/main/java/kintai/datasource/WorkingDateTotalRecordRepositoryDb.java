package kintai.datasource;

import kintai.domain.*;
import kintai.service.WorkingDateTotalRecordRepository;
import lombok.RequiredArgsConstructor;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class WorkingDateTotalRecordRepositoryDb implements WorkingDateTotalRecordRepository {
    private final Path csvPath;
    @Override
    public void save(WorkingDateTotalRecord workingDateTotalRecord,LocalDateTime now) {
        File file = new File("data.csv");
        //csvPathでファイル指定、StandardOption.APPENDで既存ファイル（作成したファイルに追加)
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
        String formatYearMonth = yearMonth.format(DateTimeFormatter.ofPattern("yyyyMM"));
        List<WorkingDateTotalRecord> workingDateTotalRecords = new ArrayList<>();
        LaborRegulations laborRegulations = LaborRegulations.DEFAULT;

        try (
                BufferedReader br =Files.newBufferedReader(csvPath)
        ) {
            String line = br.readLine();
            while (line != null) {
                String[] columns = line.split(",");
                if (!columns[0].startsWith(formatYearMonth)) {
                    line = br.readLine();
                    continue;
                }

                //Stringで渡されたcsvの内容をLocalTimeで解析したのちformat
                workingDateTotalRecords.add(new WorkingDateTotalRecord(
                        WorkDay.parseyyyyMMdd(columns[0]),
                        WorkStart.parseHHmm(columns[1]),
                        WorkEnd.parseHHmm(columns[2]),
                        new WorkMinutes(Integer.parseInt(columns[3])),
                        new OverWorkMinutes(Integer.parseInt(columns[4]))
                ));
                line = br.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return workingDateTotalRecords;
    }

}
