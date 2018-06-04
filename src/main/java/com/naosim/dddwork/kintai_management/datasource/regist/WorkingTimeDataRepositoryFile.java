package com.naosim.dddwork.kintai_management.datasource.regist;

import com.naosim.dddwork.kintai_management.domain.duty.regist.WorkingTimeDataInput;
import com.naosim.dddwork.kintai_management.domain.duty.regist.WorkingTimeDataRepository;
import com.naosim.dddwork.kintai_management.domain.duty.total.WorkingTimeTotalDataInput;
import com.naosim.dddwork.kintai_management.domain.duty.total.WorkingTimeTotalDataResult;
import com.naosim.dddwork.kintai_management.domain.word.TotalOverWorkingTime;
import com.naosim.dddwork.kintai_management.domain.word.TotalWorkingTime;
import com.naosim.dddwork.kintai_management.domain.word.TotalYearMonth;
import org.springframework.stereotype.Repository;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 勤怠情報リポジトリ実装
 */
@Repository
public class WorkingTimeDataRepositoryFile implements WorkingTimeDataRepository {

    @Override
    public void registWorkingTime(WorkingTimeDataInput workingTimeDataInput) {

        // ファイル名はプロパティファイル定義にしたい
        File file = new File("data.csv");
        try (FileWriter filewriter = new FileWriter(file, true)) {
            filewriter.write(String.format("%s,%s,%s,%s,%s,%s,%s\n",
                    workingTimeDataInput.getRegistrationDate().getFormatValue(),
                    workingTimeDataInput.getWorkingStartTime().getFormatValue(),
                    workingTimeDataInput.getWorkingEndTime().getFormatValue(),
                    workingTimeDataInput.createWorkingTime().getValue(),
                    workingTimeDataInput.createOverWoringTime().getValue(),
                    workingTimeDataInput.getHolidayKind().getFormatValue(),
                    LocalDateTime.now().toString())
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public WorkingTimeTotalDataResult getTotalWorkingTime(WorkingTimeTotalDataInput workingTimeTotalInput) {

        int totalWorkMinutes = 0;
        int totalOverWorkMinutes = 0;

        File file = new File("data.csv");

        try(
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
        ) {

            String line = br.readLine();
            Map<String, Integer> totalWorkMinutesMap = new HashMap<>();
            Map<String, Integer> totalOverWorkMinutesMap = new HashMap<>();
            while(line != null){
                String[] columns = line.split(",");
                if(!columns[0].startsWith(workingTimeTotalInput.getTotalYearMonth().getValue())) {
                    line = br.readLine();
                    continue;
                }
                totalWorkMinutesMap.put(columns[0], Integer.valueOf(columns[3]));
                totalOverWorkMinutesMap.put(columns[0], Integer.valueOf(columns[4]));

                line = br.readLine();
            }

            Set<String> keySet = totalWorkMinutesMap.keySet();
            for(String key : keySet) {
                totalWorkMinutes += totalWorkMinutesMap.get(key);
                totalOverWorkMinutes += totalOverWorkMinutesMap.get(key);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new WorkingTimeTotalDataResult(
                new TotalYearMonth(workingTimeTotalInput.getTotalYearMonth().getValue()),
                new TotalWorkingTime(totalWorkMinutes),
                new TotalOverWorkingTime(totalOverWorkMinutes)
        );
    }
}
