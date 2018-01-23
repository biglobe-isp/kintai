package com.naosim.dddwork.datasource;

import com.naosim.dddwork.domain.WorkTimeRepository;
import com.naosim.dddwork.domain.WorkTimeTotal;
import com.naosim.dddwork.api.form.WorkTimeTotalForm;
import com.naosim.dddwork.domain.WorkTimeTotalParam;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class WorkTimeTotalRepositoryFile implements WorkTimeRepository<WorkTimeTotal, WorkTimeTotalParam> {
    @Override
    public WorkTimeTotal doWorktimeTaskExecute(WorkTimeTotalParam workTimeTotalParam) {

        WorkTimeTotal workTimeTotal = null;

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
                if (!columns[0].startsWith(workTimeTotalParam.getYearMonth())) {
                    continue;
                }
                totalWorkMinutesMap.put(columns[0], Integer.valueOf(columns[3]));
                totalOverWorkMinutesMap.put(columns[0], Integer.valueOf(columns[4]));

                line = br.readLine();
            }

            workTimeTotal = new WorkTimeTotal(totalWorkMinutesMap, totalOverWorkMinutesMap);

            return workTimeTotal;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
