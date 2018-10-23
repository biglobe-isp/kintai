package datasource;
import domain.*;
import domain.japan.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Datasource層のメインの処理を担うクラス。
 */
public class Datasource implements DatasourceRepository {

    public void writeKintai (WorkDateVO workDateVO, StartTime startTime, EndTime endTime,
                             WorkTimeMinuteVO workTimeMinuteVO, OverWorkTimeMinuteVO overWorkTimeMinuteVO,
                             TimeGetterRepository timeGetterRepository) {
        try {

            //日付
            String date = workDateVO.getValue();

            //出勤時刻
            String startD = String.format("%02d%02d", startTime.getStartTimeHour(), startTime.getStartTimeMinute());

            //退勤時刻
            String endD = String.format("%02d%02d", endTime.getEndTimeHour(), endTime.getEndTimeMinute());

            //総労働時間
            int dayWorkMinute = workTimeMinuteVO.getValue();

            //総残業時間
            int dayOverWorkMinute = overWorkTimeMinuteVO.getValue();

            //現在時刻
            String now = timeGetterRepository.getNow();

            File file = new File("data.csv");
            try(FileWriter filewriter = new FileWriter(file, true)) {
                filewriter.write(String.format("%s,%s,%s,%s,%s,%s\n",
                                                date, startD, endD, dayWorkMinute, dayOverWorkMinute, now));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readKintai(WorkYearMonthVO workYearMonthVO) {

        try {

            //労働時間を取得したい年月
            String yearMonth = workYearMonthVO.getValue();

            File file = new File("data.csv");
            try (
                    FileReader fr = new FileReader(file);
                    BufferedReader br = new BufferedReader(fr)
            ) {

                String line = br.readLine();
                Map<String, Integer> totalWorkMinutesMap = new HashMap<>();
                Map<String, Integer> totalOverWorkMinutesMap = new HashMap<>();

                //▼配列columnsの各要素の内容
                // 0:日付（YYYYMMDD）
                // 1:出勤時間（HHMM）
                // 2:退勤時間（HHMM）
                // 3:労働時間（M）
                // 4:残業時間（M）
                while (line != null) {
                    String[] columns = line.split(",");
                    if (!columns[0].startsWith(yearMonth)) {
                        continue;
                    }
                    //労働時間Map（キー：日付（YYYYMMDD）、値：労働時間（M））
                    totalWorkMinutesMap.put(columns[0], Integer.valueOf(columns[3]));
                    //残業時間Map（キー：日付（YYYYMMDD）、値：残業時間（M））
                    totalOverWorkMinutesMap.put(columns[0], Integer.valueOf(columns[4]));

                    line = br.readLine();
                }

                Set<String> keySet = totalWorkMinutesMap.keySet();

                int totalWorkMinute = 0;
                int totalOverWorkMinute = 0;

                for (String key : keySet) {
                    //労働時間合計（M）を取得
                    totalWorkMinute += totalWorkMinutesMap.get(key);
                    //残業時間合計（M）を取得
                    totalOverWorkMinute += totalOverWorkMinutesMap.get(key);
                }

                System.out.println("勤務時間: " + totalWorkMinute / 60 + "時間" + totalWorkMinute % 60 + "分");
                System.out.println("残業時間: " + totalOverWorkMinute / 60 + "時間" + totalOverWorkMinute % 60 + "分");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}