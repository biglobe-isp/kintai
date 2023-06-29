package jp.co.biglobe.isp.kintai.api.controller;

import jp.co.biglobe.isp.kintai.api.InputWorkTimeForm;
import jp.co.biglobe.isp.kintai.service.workrecord_registration.WorkRecordRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class InputController {
    private final WorkRecordRegistrationService workRecordRegistrationService;

    public void run(String[] args) {
        workRecordRegistrationService.register(new InputWorkTimeForm(args).toDomain());
    }
}
