package com.naosim.dddwork.datasource;

import java.time.LocalDate;
import java.util.ArrayList;

public class WorkTimeDAO {
    private WorkTimeCSV csv;


    public WorkTimeEntity Regist(WorkTimeEntity entity) throws Exception{
        return csv.Regist(entity);
    }
    public WorkTimeEntity Update(WorkTimeEntity entity) throws Exception{
        return csv.Update(entity);
    }
    public WorkTimeEntity SelectByDate(LocalDate date) throws Exception{
        return csv.SelectByDate(date);
    }
    public ArrayList<WorkTimeEntity> SelectByMonth(LocalDate date) throws Exception{
        return csv.SelectByMonth(date);
    }

}
