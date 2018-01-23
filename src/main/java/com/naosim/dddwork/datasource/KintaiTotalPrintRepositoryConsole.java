package com.naosim.dddwork.datasource;

import com.naosim.dddwork.domain.KintaiTotal;
import com.naosim.dddwork.domain.KintaiTotalPrintRepository;
import org.springframework.stereotype.Component;

@Component
public class KintaiTotalPrintRepositoryConsole implements KintaiTotalPrintRepository {

    @Override
    public void print(KintaiTotal kintaiTotal) {
        System.out.println(this.getPrintStringForTotalWorkTime(kintaiTotal));
        System.out.println(this.getPrintStringForTotalOverWorkTime(kintaiTotal));
    }

    private String getPrintStringForTotalWorkTime(KintaiTotal kintaiTotal) {
        return this.getPrintString("勤務時間", kintaiTotal.getTotalWorkMinutes());
    }

    private String getPrintStringForTotalOverWorkTime(KintaiTotal kintaiTotal) {
        return this.getPrintString("残業時間", kintaiTotal.getTotalOverWorkMinutes());
    }

    private String getPrintString(String title, int totalTime) {
        return String.format("%s: %s時間%s分", title, totalTime / 60, totalTime % 60);
    }

}
