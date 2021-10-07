package datasource;


import domain.KintaiRegisterRepositoryFile;
import domain.OverWorkTime;
import domain.WorkDay;
import domain.WorkEndTime;
import domain.WorkStartTime;
import domain.WorkTime;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class KintaiRegisterRepositoryFileImpl implements KintaiRegisterRepositoryFile {

    @Override
    public void regist(
            WorkDay workDay,
            WorkStartTime workStartTime,
            WorkEndTime workEndTime,
            WorkTime workTime,
            OverWorkTime overWorkTime) {
        File file = new File("data.csv");
        String now = LocalDateTime.now().toString();
        try (FileWriter filewriter = new FileWriter(file, true)) {
            filewriter.write(String.format(
                    "%s,%s,%s,%s,%s,%s\n",
                    workDay.workDay,
                    workStartTime.startTime,
                    workEndTime.endTime,
                    workTime.calculate(workStartTime, workEndTime),
                    overWorkTime.calculate(workStartTime, workEndTime),
                    now
            ));
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    public void countUp(String value) {
        String yearMonth = value;
        int totalWorkMinutes = 0;
        int totalOverWorkMinutes = 0;

        File file = new File("data.csv");

        try (
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr)
        ) {
            String line = br.readLine();
            Map<String, Integer> totalWorkMinutesMap = new HashMap<>();
            Map<String, Integer> totalOverWorkMinutesMap = new HashMap<>();
            while (line != null) {
                String[] columns = line.split(",");
                if (!columns[0].startsWith(yearMonth)) {
                    continue;
                }
                totalWorkMinutesMap.put(columns[0], Integer.valueOf(columns[3]));
                totalOverWorkMinutesMap.put(columns[0], Integer.valueOf(columns[4]));

                line = br.readLine();
            }

            Set<String> keySet = totalWorkMinutesMap.keySet();
            for (String key : keySet) {
                totalWorkMinutes += totalWorkMinutesMap.get(key);
                totalOverWorkMinutes += totalOverWorkMinutesMap.get(key);
            }

            System.out.println("勤務時間: " + totalWorkMinutes / 60 + "時間" + totalWorkMinutes % 60 + "分");
            System.out.println("残業時間: " + totalOverWorkMinutes / 60 + "時間" + totalOverWorkMinutes % 60 + "分");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
