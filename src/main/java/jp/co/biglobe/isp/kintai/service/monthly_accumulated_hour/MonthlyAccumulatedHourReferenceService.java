package jp.co.biglobe.isp.kintai.service.monthly_accumulated_hour;

import jp.co.biglobe.isp.kintai.domain.monthly_accumulated_hour.MonthlyAccumulatedWorkMinutes;
import jp.co.biglobe.isp.kintai.domain.monthly_accumulated_hour.MonthlyWorkRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MonthlyAccumulatedHourReferenceService {
    private final MonthlyWorkRecordRepository monthlyWorkRecordRepository;
    public MonthlyAccumulatedWorkMinutes refer(String yearMonth) {
        var monthlyWorkRecord = monthlyWorkRecordRepository.refer(yearMonth);
        return MonthlyAccumulatedWorkMinutes.from(monthlyWorkRecord);
    }
}
