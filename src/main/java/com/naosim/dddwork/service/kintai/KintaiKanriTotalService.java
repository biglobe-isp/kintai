//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.naosim.dddwork.service.kintai;

import com.naosim.dddwork.domain.duty.WorkingDataRepository;
import com.naosim.dddwork.domain.duty.WorkingSummaryData;
import com.naosim.dddwork.service.input.KintaiKanriTotalServiceInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KintaiKanriTotalService {
    @Autowired
    private WorkingDataRepository workingDataRepository;

    public KintaiKanriTotalService() {
    }

    public void execute(KintaiKanriTotalServiceInput kintaiKanriTotalServiceInput) throws Exception {
        WorkingSummaryData workingSummaryData = this.workingDataRepository.summary(this.workingDataRepository.read());
        System.out.println("集計期間：" + workingSummaryData.getWorkDateStart().getValue() + " 〜 " + workingSummaryData.getWorkDateEnd().getValue());
        System.out.println("勤務時間：" + workingSummaryData.getWorkingMinuteSummary().getValue() + "時間");
        System.out.println("残業時間：" + workingSummaryData.getOverWorkingMinuteSummary().getValue() + "時間");
    }
}
