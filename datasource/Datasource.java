package datasource;

import domain.DatasourceRepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Datasource implements DatasourceRepository {

//    //労働時間合計（M)と残業時間合計（M）を持つ配列
//    private int[] totalNums = null;
//    //１ヶ月分の労働時間合計（M）
//    int totalWorkMinutes = 0;
//    //１ヵ月分の残業時間合計（M）
//    int totalOverWorkMinutes = 0;

    public void writeData(String date, int start, int end, int workMinutes, int overWorkMinutes, String now) {
        try {
            File file = new File("data.csv");
            try(FileWriter filewriter = new FileWriter(file, true)) {
                filewriter.write(String.format("%s,%s,%s,%s,%s,%s\n", date, start, end, workMinutes, overWorkMinutes, now));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int[] readData(String yearMonth) {

        int[] totalNums = new int[2];
        try {

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
                // 1:開始時刻（MMDD）
                // 2:終了時刻（MMDD）
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

                totalNums = new int[]{totalWorkMinute, totalOverWorkMinute};
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalNums;
    }
}
