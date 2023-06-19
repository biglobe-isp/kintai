package jp.co.biglobe.isp.kintai.service.monthly_accumulated_hour;

import jp.co.biglobe.isp.kintai.domain.monthly_accumulated_hour.MonthlyWorkRecord;

public interface MonthlyWorkRecordRepository {
    MonthlyWorkRecord refer(String yearMonth);
}
