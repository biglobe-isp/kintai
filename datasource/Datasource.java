package datasource;

import api.TimeGetter;
import domain.DatasourceRepository;
import domain.DateVO;
import domain.TimeVO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Datasource implements DatasourceRepository {

    public void writeData (DateVO dateVO, TimeVO timeVO, TimeGetter timeGetter) {
        try {

            //日付
            String date = dateVO.getDate();

            //出勤時刻
            String startD = timeVO.getStartD();

            //退勤時刻
            String endD = timeVO.getEndD();

            int startH = Integer.valueOf(timeVO.getStartD().substring(0, 2));
            int startM = Integer.valueOf(timeVO.getStartD().substring(2, 4));
            int endH = Integer.valueOf(timeVO.getEndD().substring(0, 2));
            int endM = Integer.valueOf(timeVO.getEndD().substring(2, 4));

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

    public void readData(DateVO dateVO) {

        try {

            //労働時間を取得したい年月
            String yearMonth = dateVO.getDate();

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
