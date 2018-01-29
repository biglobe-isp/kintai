package com.naosim.dddwork.datasource;

import com.naosim.dddwork.domain.worktotal.TotalNormalWorkMinutes;
import com.naosim.dddwork.domain.worktotal.TotalOverWorkMinutes;
import com.naosim.dddwork.domain.worktotal.WorkMinutesPerYMD;
import com.naosim.dddwork.domain.worktotal.WorkTimeTotal;
import com.naosim.dddwork.domain.worktotal.WorkTimeTotalRepository;
import com.naosim.dddwork.domain.worktotal.WorkDateAndTimeTotal;

import java.io.*;

public class WorkTimeTotalRepositoryFile implements WorkTimeTotalRepository {
    @Override
    public WorkTimeTotal doWorktimeTaskExecute(WorkDateAndTimeTotal workDateAndTimeTotal) {
        File file = new File("data.csv");

        try (
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
        ) {

            String line = br.readLine();
            TotalNormalWorkMinutes totalNormalWorkMinutes = new TotalNormalWorkMinutes();
            TotalOverWorkMinutes totalOverWorkMinutes = new TotalOverWorkMinutes();

            while (line != null) {
                String[] columns = line.split(",");
                if (!columns[0].startsWith(workDateAndTimeTotal.getWorkTotalYeaAndMonth().getYearMonth())) {
                    continue;
                }
                totalNormalWorkMinutes.getTotalWorkMinutesMap().put(new WorkMinutesPerYMD(columns[0]), Integer.valueOf(columns[3]));
                totalOverWorkMinutes.getOverWorkMinutesMap().put(new WorkMinutesPerYMD(columns[0]), Integer.valueOf(columns[4]));

                line = br.readLine();
            }

            WorkTimeTotal workTimeTotal = new WorkTimeTotal(totalNormalWorkMinutes, totalOverWorkMinutes);

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
