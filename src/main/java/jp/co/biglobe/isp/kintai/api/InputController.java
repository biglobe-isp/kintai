package jp.co.biglobe.isp.kintai.api;

import jp.co.biglobe.isp.kintai.domain.attendance_record.WorkTime;
import jp.co.biglobe.isp.kintai.domain.input_worktime.InputWorkTime;
import jp.co.biglobe.isp.kintai.service.workrecord_registration.WorkRecordRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequiredArgsConstructor
public class InputController {
    private final WorkRecordRegistrationService workRecordRegistrationService;

    public void run(String[] args) {
        String workDateStr = args[1];
        String openingTimeStr = args[2];
        String closingTimeStr = args[3];

        var workDate = LocalDate.parse(workDateStr, DateTimeFormatter.ofPattern("yyyyMMdd"));
        var openingTime = LocalTime.parse(openingTimeStr, DateTimeFormatter.ofPattern("HHmm"));
        var closingTime = LocalTime.parse(closingTimeStr, DateTimeFormatter.ofPattern("HHmm"));

        WorkTimeValidator.isValid(openingTime, closingTime);

        var workTime = new WorkTime(openingTime, closingTime);
        var inputWorkTime = new InputWorkTime(workDate, workTime);
        workRecordRegistrationService.register(inputWorkTime);
    }
}
