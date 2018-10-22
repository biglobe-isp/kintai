package datasource;

import domain.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RepositoryDb implements IRepository {

    @Override
    public void writeData(DateVO date, StartVO start, EndVO end, CalcWorkTimeVO workTime, INowRepository iNowRepo) {

        //DateVO date, StartVO start, EndVO end, CalcWorkTimeVO workTime, IRepository iRepo
        File file = new File("data.csv");
        try (FileWriter filewriter = new FileWriter(file, true)) { //TODO 引数問題
            filewriter.write(String.format("%s,%s,%s,%s,%s,%s\n", date.getValue(), start.getValue(), end.getValue(), workTime.getWorkTime(), workTime.getOverWorTime(), iNowRepo.getCurrentTime()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void readData() {

//        int totalWorkMinutes = 0;
//        int totalOverWorkMinutes = 0;

//      String yearMonth = args[1]; //TODO args[1]をどうするか
        File file = new File("data.csv");

//        try (
//                FileReader fr = new FileReader(file);
//                BufferedReader br = new BufferedReader(fr);
//        ) {
//
//            String line = br.readLine();
//            Map<String, Integer> totalWorkMinutesMap = new HashMap<>();
//            Map<String, Integer> totalOverWorkMinutesMap = new HashMap<>();
//            while (line != null) {
//                String[] columns = line.split(",");
//                if (!columns[0].startsWith(yearMonth)) {
//                    continue;
//                }
//                totalWorkMinutesMap.put(columns[0], Integer.valueOf(columns[3]));
//                totalOverWorkMinutesMap.put(columns[0], Integer.valueOf(columns[4]));
//
//                line = br.readLine();
//            }
//
//            //TODO VOに記述する
//
//
//            Set<String> keySet = totalWorkMinutesMap.keySet();
//            for (String key : keySet) {
//                totalWorkMinutes += totalWorkMinutesMap.get(key);
//                totalOverWorkMinutes += totalOverWorkMinutesMap.get(key);
//            }
//
//
//        } catch (FileNotFoundException e1) {
//            e1.printStackTrace();
//
//        } catch (IOException e2) {
//            e2.printStackTrace();
//        }
    }

}
