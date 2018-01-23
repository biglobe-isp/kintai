package com.naosim.dddwork.api;

import com.naosim.dddwork.api.form.KintaiRegistInputForm;
import com.naosim.dddwork.api.form.KintaiTotalPrintInputForm;
import com.naosim.dddwork.domain.KintaiRegistInput;
import com.naosim.dddwork.domain.KintaiTotalPrintInput;
import com.naosim.dddwork.service.KintaiRegistService;
import com.naosim.dddwork.service.KintaiTotalPrintService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KintaiKanriApi {

    private final KintaiRegistService kintaiRegistService;

    private final KintaiTotalPrintService kintaiTotalPrintService;

    public void execute(String[] args) throws IOException {

        if (!this.isExistArgsElement(args)) {
            throw new RuntimeException("引数が足りません");
        }

        MethodType methodType = MethodType.getMethodTypeFromString(args[0]);

        switch (methodType) {
            case INPUT:
                KintaiRegistInputForm inputKintaiForm = new KintaiRegistInputForm(args);
                KintaiRegistInput kintaiRegistInput = inputKintaiForm.getValueObject();
                this.kintaiRegistService.registKintaiOfOneDay(kintaiRegistInput);
                break;

            case TOTAL:
                KintaiTotalPrintInputForm kintaiTotalPrintInputForm = new KintaiTotalPrintInputForm(args);
                KintaiTotalPrintInput kintaiTotalPrintInput = kintaiTotalPrintInputForm.getValueObject();
                this.kintaiTotalPrintService.printTargetMonth(kintaiTotalPrintInput);
                break;

            default:
        }
    }

    private boolean isExistArgsElement(String[] args) {
        return args.length >= 1;
    }

}
