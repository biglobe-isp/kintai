package com.naosim.dddwork.api;

import com.naosim.dddwork.api.form.KintaiRegistInputForm;
import com.naosim.dddwork.api.form.KintaiTotalPrintInputForm;
import com.naosim.dddwork.domain.kintai.KintaiTotalPrintTargetYearMonth;
import com.naosim.dddwork.domain.kintai.WorkStartAndEndTimeOfOneDay;
import com.naosim.dddwork.service.KintaiRegistService;
import com.naosim.dddwork.service.KintaiTotalPrintService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KintaiKanriApi {

    private final KintaiRegistService kintaiRegistService;

    private final KintaiTotalPrintService kintaiTotalPrintService;

    public void execute(String[] args) {

        if (!this.isExistArgsElement(args)) {
            throw new RuntimeException("引数が足りません");
        }

        MethodType methodType = MethodType.getMethodTypeFromString(args[0]);

        switch (methodType) {
            case INPUT:
                KintaiRegistInputForm kintaiRegistInputForm = new KintaiRegistInputForm(args);
                WorkStartAndEndTimeOfOneDay workStartAndEndTimeOfOneDay = kintaiRegistInputForm.getValueObject();
                this.kintaiRegistService.registKintaiOfOneDay(workStartAndEndTimeOfOneDay);
                break;

            case TOTAL:
                KintaiTotalPrintInputForm kintaiTotalPrintInputForm = new KintaiTotalPrintInputForm(args);
                KintaiTotalPrintTargetYearMonth kintaiTotalPrintTargetYearMonth = kintaiTotalPrintInputForm.getValueObject();
                this.kintaiTotalPrintService.printTargetMonth(kintaiTotalPrintTargetYearMonth);
                break;

            default:
        }
    }

    private boolean isExistArgsElement(String[] args) {
        return args.length >= 1;
    }

}
