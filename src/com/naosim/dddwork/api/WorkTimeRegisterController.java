package com.naosim.dddwork.api;

import com.naosim.dddwork.domain.Hour;
import com.naosim.dddwork.domain.Minute;
import com.naosim.dddwork.domain.WorkDataRepository;
import com.naosim.dddwork.domain.WorkTime;
import com.naosim.dddwork.service.WorkTimeRegisterService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WorkTimeRegisterController {
    WorkTimeRegisterService registerWorkTimeService;

    public WorkTimeRegisterController(WorkDataRepository workDataRepository) {
        this.registerWorkTimeService = new WorkTimeRegisterService(workDataRepository);
    }

    // 値を受け取る
    public void invoke(String workDate, String startTime, String endTime) {
        // StringをパースしてWorkTimeにする
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuuMMdd");
        LocalDate parsingWorkDate = LocalDate.parse(workDate, formatter);
        Hour startTimeHour = new Hour(Integer.parseInt(startTime.substring(0, 2)));
        Minute startTimeMinute = new Minute(Integer.parseInt(startTime.substring(2, 4)));
        Hour endTimeHour = new Hour(Integer.parseInt(endTime.substring(0, 2)));
        Minute endTimeMinute = new Minute(Integer.parseInt(endTime.substring(2, 4)));

        WorkTime workTime = new WorkTime(
                parsingWorkDate,
                startTimeHour,
                startTimeMinute,
                endTimeHour,
                endTimeMinute
        );

        registerWorkTimeService.registerWorkTime(workTime);
    }
}
