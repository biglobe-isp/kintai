package com.naosim.dddwork.service.kintai;

import com.naosim.dddwork.domain.duty.CalculateTime;
import com.naosim.dddwork.domain.duty.WorkingDataRepository;
import com.naosim.dddwork.service.input.KintaiKanriInputServiceInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 勤務管理登録Service
 */

@Service
public class KintaiKanriInputService {

    @Autowired
    private WorkingDataRepository workingDataRepository;

    public void execute(KintaiKanriInputServiceInput kintaiKanriInputServiceInput) throws Exception {

        // 勤務時間、残業時間を計算して結果をファイル出力
        workingDataRepository.write(
                new CalculateTime(
                        kintaiKanriInputServiceInput.getWorkTimeFrom(),
                        kintaiKanriInputServiceInput.getWorkTimeTo(),
                        kintaiKanriInputServiceInput.getWorkTimeFrom().getHour(),
                        kintaiKanriInputServiceInput.getWorkTimeFrom().getMinute(),
                        kintaiKanriInputServiceInput.getWorkTimeTo().getHour(),
                        kintaiKanriInputServiceInput.getWorkTimeTo().getMinute()
                ).makeWorkingData(
                        kintaiKanriInputServiceInput.getWorkDate()
                )
        );
    }
}
