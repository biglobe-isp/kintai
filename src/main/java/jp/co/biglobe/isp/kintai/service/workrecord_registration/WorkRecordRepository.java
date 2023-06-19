package jp.co.biglobe.isp.kintai.service.workrecord_registration;

import jp.co.biglobe.isp.kintai.domain.attendance_record.WorkRecord;
public interface WorkRecordRepository {
    void persist(WorkRecord workRecord);
}
