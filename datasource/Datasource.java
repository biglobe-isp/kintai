package datasource;

import api.TimeGetter;
import domain.*;

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

    public void writeData (WorkDateVO workDateVO, StartTimeVO startTimeVO, EndTimeVO endTimeVO, TimeGetter timeGetter) {
        try {

            //日付
            String date = workDateVO.getWorkDate().substring(6, 14);

            //出勤時刻
            String startD = startTimeVO.getStartTimeD().substring(7, 11);

            //退勤時刻
            String endD = endTimeVO.getEndTimeD().substring(5, 9);

            int startH = Integer.valueOf(startTimeVO.getStartTimeD().substring(7, 9));
            int startM = Integer.valueOf(startTimeVO.getStartTimeD().substring(9, 11));
            int endH = Integer.valueOf(endTimeVO.getEndTimeD().substring(5, 7));
            int endM = Integer.valueOf(endTimeVO.getEndTimeD().substring(7, 9));

            WorkTimeCalculator workTimeCalculator = new WorkTimeCalculator();
            //総労働時間
            int dayWorkMinute = workTimeCalculator.calcDayWorkMinute(startH, startM, endH, endM);

            OverWorkTimeCalculator overWorkTimeCalculator = new OverWorkTimeCalculator();
            //総残業時間
            int dayOverWorkMinute = overWorkTimeCalculator.calcDayOverWorkMinute(dayWorkMinute);

            //現在時刻
            String now = timeGetter.getNow();

            File file = new File("data.csv");
            try(FileWriter filewriter = new FileWriter(file, true)) {
                filewriter.write(String.format("%s,%s,%s,%s,%s,%s\n", date, startD, endD, dayWorkMinute, dayOverWorkMinute, now));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readData(WorkYearMonthVO workYearMonthVO) {

        try {

            //労働時間を取得したい年月
            String yearMonth = workYearMonthVO.getWorkYearMonth().substring(6, 12);

            File file = new File("data.csv");
            try (
                    FileReader fr = new FileReader(file);
                    BufferedReader br = new BufferedReader(fr)
            ) {

                String line = br.readLine();
                Map<String, Integer> totalWorkMinutesMap = new HashMap<>();
                Map<String, Integer> totalOverWorkMinutesMap = new HashMap<>();

                //配列columns
                // 0:日付（YYYYMMDD）
                // 1:開始時刻（HHMM）
                // 2:終了時刻（HHMM）
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
