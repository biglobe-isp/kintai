package com.naosim.dddwork.api;

import com.naosim.dddwork.api.form.InputKintaiForm;
import com.naosim.dddwork.domain.InputKintai;
import com.naosim.dddwork.service.KintaiRegistService;
import com.naosim.dddwork.service.TotalKintaiPrintService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KintaiKanriApi {

    private final KintaiRegistService kintaiRegistService;

    private final TotalKintaiPrintService totalKintaiPrintService;

    public void execute(String[] args) throws IOException {

        InputKintaiForm inputKintaiForm = new InputKintaiForm(args);
        InputKintai inputKintai = inputKintaiForm.getValueObject();

        switch (inputKintai.getMethodType()) {
            case INPUT:
                this.kintaiRegistService.registKintaiOfOneDay(inputKintai);
                break;

            case TOTAL:
                this.totalKintaiPrintService.printTargetMonth(inputKintai);
                break;

            default:
        }
    }

}
