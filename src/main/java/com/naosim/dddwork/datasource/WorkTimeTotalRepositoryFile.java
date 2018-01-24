package com.naosim.dddwork.datasource;

import com.naosim.dddwork.domain.worktotal.WorkTimeTotalCalculation;
import com.naosim.dddwork.domain.worktotal.WorkTimeTotalRepository;
import com.naosim.dddwork.domain.worktotal.WorkDateAndTimeTotal;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class WorkTimeTotalRepositoryFile implements WorkTimeTotalRepository {
    @Override
    public WorkTimeTotalCalculation doWorktimeTaskExecute(WorkDateAndTimeTotal workDateAndTimeTotal) {
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
                if (!columns[0].startsWith(workDateAndTimeTotal.getWorkTotalYeaAndMonth().getYearMonth())) {
                    continue;
                }
                System.out.println(columns[0] + " >>>> " + columns[3]);
                System.out.println(columns[0] + ">>>> " + columns[3]);
                totalWorkMinutesMap.put(columns[0], Integer.valueOf(columns[3]));
                totalOverWorkMinutesMap.put(columns[0], Integer.valueOf(columns[4]));

                line = br.readLine();
            }
            WorkTimeTotalCalculation workTimeTotalCollection = new WorkTimeTotalCalculation(totalWorkMinutesMap, totalOverWorkMinutesMap);

            return workTimeTotalCollection;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
