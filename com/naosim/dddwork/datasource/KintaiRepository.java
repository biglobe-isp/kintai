package com.naosim.dddwork.datasource;

import com.naosim.dddwork.domain.Kintai;
import com.naosim.dddwork.domain.IKintaiRepository;
import com.naosim.dddwork.domain.KintaiDate;
import com.naosim.dddwork.domain.KintaiOverWorkMinutes;
import com.naosim.dddwork.domain.KintaiWorkMinutes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class KintaiRepository implements IKintaiRepository {

    public void save(Kintai kintai) {
        File file = new File("data.csv");
        try (FileWriter filewriter = new FileWriter(file, true)) {
            filewriter.write(String.format(
                    "%s,%s,%s,%s,%s,%s\n",
                    kintai.getKintaiDate().getValue(),
                    kintai.getKintaiStart().getValue(),
                    kintai.getKintaiEnd().getValue(),
                    kintai.getKintaiWorkMinutes().getValue(),
                    kintai.getKintaiOverWorkMinutes().getValue(),
                    kintai.getkintaiRegisterTime().getValue()
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<KintaiDate, KintaiWorkMinutes> getTotalWorkMinutesMapOf(String yearMonth) {
        Map<KintaiDate, KintaiWorkMinutes> totalWorkMinutesMap = new HashMap<>();

        File file = new File("data.csv");

        try (
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr)
        ) {
            String line = br.readLine();
            while (line != null) {
                String[] columns = line.split(",");
                if (!columns[0].startsWith(yearMonth)) {
                    line = br.readLine();
                    continue;
                }
                totalWorkMinutesMap.put(new KintaiDate(columns[0]), new KintaiWorkMinutes(Integer.valueOf(columns[3])));

                line = br.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalWorkMinutesMap;
    }


    public Map<KintaiDate, KintaiOverWorkMinutes> getTotalOverWorkMinutesMapOf(String yearMonth) {
        Map<KintaiDate, KintaiOverWorkMinutes> totalOverWorkMinutesMap = new HashMap<>();

        File file = new File("data.csv");

        try (
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr)
        ) {
            String line = br.readLine();
            while (line != null) {
                String[] columns = line.split(",");
                if (!columns[0].startsWith(yearMonth)) {
                    line = br.readLine();
                    continue;
                }
                totalOverWorkMinutesMap.put(new KintaiDate(columns[0]), new KintaiOverWorkMinutes(Integer.valueOf(columns[4])));

                line = br.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalOverWorkMinutesMap;
    }

}
