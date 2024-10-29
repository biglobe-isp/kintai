package com.naosim.dddwork.datasource;

import java.time.LocalDate;
import java.util.ArrayList;

public interface WorkTimeRepository {
    public WorkTimeEntity regist(WorkTimeEntity entity) throws Exception;
    public WorkTimeEntity update(WorkTimeEntity entity) throws Exception;
    public WorkTimeEntity selectByDate(LocalDate date) throws Exception;
    public ArrayList<WorkTimeEntity> selectByMonth(LocalDate date) throws Exception;
    public ArrayList<WorkTimeEntity> selectAll() throws Exception;
}
