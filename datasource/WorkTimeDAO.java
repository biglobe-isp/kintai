package com.naosim.dddwork.datasource;

import java.time.LocalDate;
import java.util.ArrayList;

public class WorkTimeDAO {
    private final WorkTimeCSV csv = new WorkTimeCSV();


    public WorkTimeEntity regist(WorkTimeEntity entity) throws Exception{
        return csv.regist(entity);
    }
    public WorkTimeEntity update(WorkTimeEntity entity) throws Exception{
        return csv.update(entity);
    }
    public WorkTimeEntity selectByDate(LocalDate date) throws Exception{
        return csv.selectByDate(date);
    }
    public ArrayList<WorkTimeEntity> selectByMonth(LocalDate date) throws Exception{
        return csv.selectByMonth(date);
    }
    public ArrayList<WorkTimeEntity> selectAll() throws Exception{
        return csv.selectAll();
    }

}
