package com.naosim.dddwork.kintai_management.datasource.total;

import com.naosim.dddwork.kintai_management.domain.duty.total.WorkingTimeTotalInput;
import com.naosim.dddwork.kintai_management.domain.duty.total.WorkingTimeTotalRepository;
import com.naosim.dddwork.kintai_management.domain.duty.total.WorkingTimeTotalResult;
import com.naosim.dddwork.kintai_management.domain.word.TotalOverWorkingTime;
import com.naosim.dddwork.kintai_management.domain.word.TotalWorkingTime;
import com.naosim.dddwork.kintai_management.domain.word.TotalYearMonth;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 */
@Repository
public class WorkingTimeTotalRepositoryFile implements WorkingTimeTotalRepository {

    @Override
    public WorkingTimeTotalResult totalWorkingTime(WorkingTimeTotalInput workingTimeTotalInput) {

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

//            System.out.println("勤務時間: " + totalWorkMinutes / 60 + "時間" + totalWorkMinutes % 60 + "分");
//            System.out.println("残業時間: " + totalOverWorkMinutes / 60 + "時間" + totalOverWorkMinutes % 60 + "分");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new WorkingTimeTotalResult(
                     new TotalYearMonth(workingTimeTotalInput.getTotalYearMonth().getValue()),
                     new TotalWorkingTime(totalWorkMinutes),
                     new TotalOverWorkingTime(totalOverWorkMinutes)
                );

    }

    @Override
    public void registTotalWorkingTime(WorkingTimeTotalResult workingTimeTotalResult) {

        File file = new File("totalData.csv");
        try (FileWriter filewriter = new FileWriter(file, true)) {
            filewriter.write(String.format("%s,%s,%s\n",
                    "集計年月日:" + workingTimeTotalResult.getTotalYearMonth().getValue(),
                    "勤務時間:" + workingTimeTotalResult.getTotalWorkingTime().getValue() / 60 + "時間" + workingTimeTotalResult.getTotalWorkingTime().getValue() % 60 + "分",
                    "残業時間:" + workingTimeTotalResult.getTotalOverWorkingTime().getValue() / 60 + "時間" + workingTimeTotalResult.getTotalOverWorkingTime().getValue() % 60 + "分"
                    )
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
