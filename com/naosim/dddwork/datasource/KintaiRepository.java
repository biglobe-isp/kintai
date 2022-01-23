package com.naosim.dddwork.datasource;

import com.naosim.dddwork.domain.entity.Kintai;
import com.naosim.dddwork.domain.IKintaiRepository;
import com.naosim.dddwork.domain.valueObject.KintaiDate;
import com.naosim.dddwork.domain.valueObject.KintaiOverWorkMinutes;
import com.naosim.dddwork.domain.valueObject.KintaiWorkMinutes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class KintaiRepository implements IKintaiRepository {

    private static final File FILE = new File("data.csv");

    public void save(Kintai kintai) {
        try (FileWriter filewriter = new FileWriter(FILE, true)) {
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

        try (
                FileReader fr = new FileReader(FILE);
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

        try (
                FileReader fr = new FileReader(FILE);
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
