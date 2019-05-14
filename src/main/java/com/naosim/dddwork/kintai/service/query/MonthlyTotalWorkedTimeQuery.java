package com.naosim.dddwork.kintai.service.query;

import com.naosim.dddwork.kintai.domain.model.foundation.date.AttendanceYearMonth;
import com.naosim.dddwork.kintai.shared.exception.SystemException;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * ［指定月の勤務時間合計表示］サービス
 */
public class MonthlyTotalWorkedTimeQuery {

    @Value
    @AllArgsConstructor(staticName="of")
    public static class Parameter {

        final AttendanceYearMonth attendanceYearMonth;
    }

    public void execute(Parameter parameter) {

        try {
            _showWorkedTime(parameter);
        }
        catch (IOException e) {
            throw new SystemException("月次勤務時間合計照会処理中に入出力例外が発生しました．", e);
        }
    }

    private static void _showWorkedTime(Parameter parameter) throws IOException {

        AttendanceYearMonth yearMonth = parameter.attendanceYearMonth;


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
                if(!columns[0].startsWith(yearMonth.getYearMonth().format(DateTimeFormatter.ofPattern("uuuuMM")))) {
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

            System.out.println("勤務時間: " + totalWorkMinutes / 60 + "時間" + totalWorkMinutes % 60 + "分");
            System.out.println("残業時間: " + totalOverWorkMinutes / 60 + "時間" + totalOverWorkMinutes % 60 + "分");
        }
    }
}
