//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.naosim.dddwork.service.kintai;

import com.naosim.dddwork.domain.duty.CalculateTime;
import com.naosim.dddwork.domain.duty.WorkingDataRepository;
import com.naosim.dddwork.service.input.KintaiKanriInputServiceInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KintaiKanriInputService {
    @Autowired
    private WorkingDataRepository workingDataRepository;

    public KintaiKanriInputService() {
    }

    public void execute(KintaiKanriInputServiceInput kintaiKanriInputServiceInput) throws Exception {
        this.workingDataRepository.write((new CalculateTime(kintaiKanriInputServiceInput.getWorkTimeFrom(), kintaiKanriInputServiceInput.getWorkTimeTo(), kintaiKanriInputServiceInput.getWorkTimeFrom().getHour(), kintaiKanriInputServiceInput.getWorkTimeFrom().getMinute(), kintaiKanriInputServiceInput.getWorkTimeTo().getHour(), kintaiKanriInputServiceInput.getWorkTimeTo().getMinute())).makeWorkingData(kintaiKanriInputServiceInput.getWorkDate()));
    }
}
