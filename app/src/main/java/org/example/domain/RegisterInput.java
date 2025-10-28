package org.example.domain;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class RegisterInput {
    String startTimeString;
    String endTimeString;
    String dateToRegisterString;

    public WorkInformation toWorkInformation() {
        WorkTime workTime = new WorkTime(
                WorkStartTime.of(startTimeString),
                WorkEndTime.of(endTimeString)
        );

        DateToRegister dateToRegister = DateToRegister.of(dateToRegisterString);

        TotalHours totalHours = TotalHours.of(workTime);

        TimestampOfTheRegistration timestampOfTheRegistration = new TimestampOfTheRegistration(LocalDateTime.now());

        return new WorkInformation(
                workTime,
                totalHours,
                dateToRegister,
                timestampOfTheRegistration
        );
    }
}
