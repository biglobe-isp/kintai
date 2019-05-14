package com.naosim.dddwork.kintai.datasource.workedtime.csv.query;

import com.naosim.dddwork.kintai.domain.model.foundation.date.AttendanceYearMonth;
import com.naosim.dddwork.kintai.domain.model.timerecord.derived.MonthlyTotalWorkedTime;
import com.naosim.dddwork.kintai.shared.exception.SystemException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * ［指定月の勤務時間合計］クエリ (CSVデータストア)
 */
public class MonthlyTotalWorkedTimeQuery {

    public MonthlyTotalWorkedTime totalWorkedTimeIn(AttendanceYearMonth yearMonth) {

        int totalWorkMinutes = 0;
        int totalOverWorkMinutes = 0;

        File file = new File("data.csv");
        Map<String, Integer> totalWorkMinutesMap = new HashMap<>();
        Map<String, Integer> totalOverWorkMinutesMap = new HashMap<>();

        try(FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr)
        ) {

            String line = br.readLine();
            while(line != null){
                String[] columns = line.split(",");
                if(!columns[0].startsWith(yearMonth.getYearMonth().format(DateTimeFormatter.ofPattern("uuuuMM")))) {
                    line = br.readLine();
                    continue;
                }
                totalWorkMinutesMap.put(columns[0], Integer.valueOf(columns[3]));
                totalOverWorkMinutesMap.put(columns[0], Integer.valueOf(columns[4]));

                line = br.readLine();
            }
        }
        catch (FileNotFoundException e) {
            throw new SystemException("ファイルが存在しません．", e);
        }
        catch (IOException e) {
            throw new SystemException("IO例外発生．", e);
        }

        Set<String> keySet = totalWorkMinutesMap.keySet();
        for(String key : keySet) {
            totalWorkMinutes += totalWorkMinutesMap.get(key);
            totalOverWorkMinutes += totalOverWorkMinutesMap.get(key);
        }

        final MonthlyTotalWorkedTime monthlyTotalWorkedTime = new MonthlyTotalWorkedTime(
                totalWorkMinutes,
                totalOverWorkMinutes);
        return monthlyTotalWorkedTime;
    }
}
