package src.com.naosim.dddwork.datasource;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import src.com.naosim.dddwork.domain.*;


public class CsvOperat implements KintaiRepository{

    private static final String FILE_NAME = "data.csv";

    public void write(Regulation kintai) {

        try (FileWriter filewriter = new FileWriter(new File(FILE_NAME), true)) {
            filewriter.write(
                    String.format("%s,%s,%s,%s,%s,%s\n",
                            kintai.getWorkingDate().getDateStr(),
                            kintai.getStart().toStartTimeFormat(),
                            kintai.getEnd().toEndTimeFormat(),
                            kintai.getWorkTime().calcWorkTime(),
                            kintai.getOrverTime().getValue(),
                            LocalDateTime.now().toString()));
        } catch (IOException e) {
            System.out.println("write処理にてIOExceptionが発生");
        }
    }


    public Map<WorkingDate, WorkTime> getTotalWorkMinutesMapOf(String yearMonth) {
        Map<WorkingDate, WorkTime> totalWorkMinutesMap = new HashMap<>();

        try (
                FileReader fr = new FileReader(FILE_NAME);
                BufferedReader br = new BufferedReader(fr)
        ) {
            String line = br.readLine();
            while (line != null) {
                String[] columns = line.split(",");
                if (!columns[0].startsWith(yearMonth)) {
                    line = br.readLine();
                    continue;
                }
                totalWorkMinutesMap.put(new WorkingDate(columns[0]), new WorkTime(Integer.valueOf(columns[3])));
                line = br.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalWorkMinutesMap;
    }
    public Map<WorkingDate, OverTime> getTotalOverWorkMinutesMapOf(String yearMonth) {
        Map<WorkingDate, OverTime> totalOverWorkMinutesMap = new HashMap<>();

        try (
                FileReader fr = new FileReader(FILE_NAME);
                BufferedReader br = new BufferedReader(fr)
        ) {
            String line = br.readLine();
            while (line != null) {
                String[] columns = line.split(",");
                if (!columns[0].startsWith(yearMonth)) {
                    line = br.readLine();
                    continue;
                }
                totalOverWorkMinutesMap.put(new WorkingDate(columns[0]), new OverTime(Integer.valueOf(columns[4])));
                line = br.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalOverWorkMinutesMap;
    }


}
