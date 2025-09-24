package com.naosim.dddwork.api;

import com.naosim.dddwork.domain.Hour;
import com.naosim.dddwork.domain.Minute;
import com.naosim.dddwork.domain.WorkDataRepository;
import com.naosim.dddwork.domain.WorkTime;
import com.naosim.dddwork.service.RegisterWorkTimeService;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RegisterWorkTimeController {
    RegisterWorkTimeService registerWorkTimeService;

    public RegisterWorkTimeController(WorkDataRepository workDataRepository) {
        this.registerWorkTimeService = new RegisterWorkTimeService(workDataRepository);
    }

    // 値を受け取る
    public void invoke(String workDate, String startTime, String endTime) {
        // StringをパースしてWorkTimeにする
        LocalDate parsingWorkDate = LocalDate.parse(workDate);
        Hour startTimeHour = new Hour(Integer.parseInt(startTime.substring(0, 2)));
        Minute startTimeMinute = new Minute(Integer.parseInt(startTime.substring(2, 4)));
        Hour endTimeHour = new Hour(Integer.parseInt(endTime.substring(0, 2)));
        Minute endTimeMinute = new Minute(Integer.parseInt(endTime.substring(2, 4)));
        LocalDateTime inputDate = LocalDateTime.now();

        WorkTime workTime = new WorkTime(
                parsingWorkDate,
                startTimeHour,
                startTimeMinute,
                endTimeHour,
                endTimeMinute,
                inputDate
        );

        //　値をサービス層のメソッドに渡す
        registerWorkTimeService.registerWorkTime(workTime);
    }
}
