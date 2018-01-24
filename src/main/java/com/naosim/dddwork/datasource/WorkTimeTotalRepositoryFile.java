package com.naosim.dddwork.datasource;

import com.naosim.dddwork.domain.WorkTimeTotal;
import com.naosim.dddwork.domain.WorkTimeTotalCalculation;
import com.naosim.dddwork.domain.WorkTimeTotalParam;
import com.naosim.dddwork.domain.WorkTimeTotalRepository;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class WorkTimeTotalRepositoryFile implements WorkTimeTotalRepository {
    @Override
    public WorkTimeTotalCalculation doWorktimeTaskExecute(WorkTimeTotalParam workTimeTotalParam) {

        WorkTimeTotal workTimeTotal = null;

        File file = new File("data.csv");

        try (
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
        ) {

            String line = br.readLine();
            Map<String, Integer> totalWorkMinutesMap = new HashMap<>();
            Map<String, Integer> totalOverWorkMinutesMap = new HashMap<>();
            Map<HashMap<String, Integer>, HashMap<String, Integer>> workTimeTotalMap = new HashMap<>();

            while (line != null) {
                String[] columns = line.split(",");
                if (!columns[0].startsWith(workTimeTotalParam.getYearMonth())) {
                    continue;
                }
                totalWorkMinutesMap.put(columns[0], Integer.valueOf(columns[3]));
                totalOverWorkMinutesMap.put(columns[0], Integer.valueOf(columns[4]));

                line = br.readLine();
            }
            //TODO ここで返す。
            WorkTimeTotalCalculation workTimeTotalCollection = new WorkTimeTotalCalculation(totalWorkMinutesMap, totalOverWorkMinutesMap);

            return workTimeTotalCollection;

            //TODO サービス層に記述する。
            //       workTimeTotal = new WorkTimeTotal(totalWorkMinutesMap, totalOverWorkMinutesMap);

            //return workTimeTotal;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
