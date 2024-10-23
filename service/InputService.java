package com.naosim.dddwork.service;

import com.naosim.dddwork.datasource.WorkTimeDAO;
import com.naosim.dddwork.datasource.WorkTimeEntity;
import com.naosim.dddwork.domain.WorkRule;
import com.naosim.dddwork.domain.WorkTimeSpecification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class InputService {
    private final WorkRule rule = new WorkRule();
    private final WorkTimeDAO dao = new WorkTimeDAO();
    public void registDailyWorkInfo(LocalDate date, LocalTime startTime, LocalTime endTime) throws Exception {
        if(startTime.isAfter(endTime)){
            throw new Exception("Start time is after end time");
        }
        if(!rule.isWorkInfoValid(date, startTime, endTime)){
            throw new Exception("WorkInfoInvalid");
        }
        WorkTimeSpecification spec = rule.getWorkTimeSpecification(date, startTime, endTime);

        WorkTimeEntity entity = dao.selectByDate(date);
        if(entity == null){
            entity = new WorkTimeEntity(date, startTime, endTime, spec.workMinutes, spec.overWorkMinutes, LocalDateTime.now());
            dao.regist(entity);
        }
        else {
            entity.date = date;
            entity.startTime = startTime;
            entity.endTime = endTime;
            dao.update(entity);
        }
    }
}
