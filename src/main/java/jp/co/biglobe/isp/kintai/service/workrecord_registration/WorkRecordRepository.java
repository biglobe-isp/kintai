package jp.co.biglobe.isp.kintai.service.workrecord_registration;

import jp.co.biglobe.isp.kintai.domain.work_record.WorkRecord;
public interface WorkRecordRepository {
    void persist(WorkRecord workRecord);
}
