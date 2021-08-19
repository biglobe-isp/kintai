package jp.co.esumit.kintai.service;

import jp.co.esumit.kintai.domain.kintai_info.KintaiInfo;
import jp.co.esumit.kintai.domain.kintai_info.KintaiInfoMaker;
import jp.co.esumit.kintai.domain.kintai_info.end_time.EndTime;
import jp.co.esumit.kintai.domain.kintai_info.office_minutes.OfficeMinutesCalculator;
import jp.co.esumit.kintai.domain.kintai_info.overtime_minutes.OvertimeMinutesCalculator;
import jp.co.esumit.kintai.domain.kintai_info.start_time.StartTime;
import jp.co.esumit.kintai.domain.kintai_info.target_day.TargetDay;
import jp.co.esumit.kintai.domain.repository.KintaiRepository;
import org.springframework.stereotype.Service;

@Service
public class KintaiInfoService {
    public void persist(TargetDay targetDay, StartTime startTime, EndTime endTime, KintaiRepository repository) {

        KintaiInfoMaker kintaiInfoMaker = new KintaiInfoMaker(
                new OfficeMinutesCalculator(),
                new OvertimeMinutesCalculator()
        );

        KintaiInfo info = kintaiInfoMaker.create(targetDay, startTime, endTime);

        repository.write(info);
    }
}
