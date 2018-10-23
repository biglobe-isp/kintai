package datasource;

import domain.japan.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FileOutDatasource implements FileOutInterface {

    @Override
    public void kintaiOutPut(DateDomain dd, StartDomain sd, EndDomain ed, WorkMinutesInterface wd) {

        File file = new File("data.csv");
        try (FileWriter filewriter = new FileWriter(file, true)) {
            filewriter.write(String.format("%s,%s,%s,%s,%s,%s\n",
                    dd.getDate().getValue(),
                    sd.getStart().getValue(),
                    ed.getEnd().getValue(),
                    wd.getWorkMinutesVal(),
                    wd.getOverWorkMinutesVal(),
                    dd.getNow().getNowVal()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void totalOutPut(DateDomain dd) {

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
                if (!columns[0].startsWith(dd.getDate().getValue())) {
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
            System.out.println("残業時間: " + totalOverWorkMinutes / 60 + "時間" + totalOverWorkMinutes % 60 + "分");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
