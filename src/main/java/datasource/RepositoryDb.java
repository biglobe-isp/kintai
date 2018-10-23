package datasource;

import domain.Japan.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RepositoryDb implements IRepository {

    @Override
    public void writeData(DateVO date, StartVO start, EndVO end, ICalcWorkTimeVO iWorkTime, INowRepository iNowRepo) {

        //DateVO date, StartVO start, EndVO end, CalcWorkTimeVO workTime, IRepository iRepo
        File file = new File("data.csv");
        try (FileWriter filewriter = new FileWriter(file, true)) { //TODO 引数問題
            filewriter.write(String.format("%s,%s,%s,%s,%s,%s\n", date.getValue(), start.getValue(), end.getValue(), iWorkTime.getWorkTime(), iWorkTime.getOverWorkTime(), iNowRepo.getCurrentTime()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void readData(DateVO date) {

        File file = new File("data.csv");

        try (
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
        ) {

            String line = br.readLine();
            Map<String, Integer> totalWorkMinutesMap = new HashMap<>();
            Map<String, Integer> totalOverWorkMinutesMap = new HashMap<>();
            while (line != null) {
                String[] columns = line.split(",");
                if (!columns[0].startsWith(date.getValue())) {
                    continue;
                }
                totalWorkMinutesMap.put(columns[0], Integer.valueOf(columns[3]));
                totalOverWorkMinutesMap.put(columns[0], Integer.valueOf(columns[4]));

                line = br.readLine();
            }


            int totalWorkMinutes = 0;
            int totalOverWorkMinutes = 0;

            Set<String> keySet = totalWorkMinutesMap.keySet();
            for (String key : keySet) {
                totalWorkMinutes += totalWorkMinutesMap.get(key);
                totalOverWorkMinutes += totalOverWorkMinutesMap.get(key);
            }

            System.out.println("勤務時間: " + totalWorkMinutes / 60 + "時間" + totalWorkMinutes % 60 + "分");
            System.out.println("残業時間: " + totalOverWorkMinutes / 60 + "時間" + totalOverWorkMinutes % 60 + "分"); //TODO 出力するのはDataSource層ではない


        } catch (FileNotFoundException e1) { //TODO e1を適した名称に変更する。
            e1.printStackTrace();

        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

}
