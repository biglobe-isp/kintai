package kintai.service;

import kintai.domain.WorkingDateTotalRecord;

import java.time.YearMonth;
import java.util.List;

public interface WorkingDateTotalRecordRepository {
    void save(WorkingDateTotalRecord workingDateTotalRecord );
    //yearMonthを入れた時Listで返却する。
    List<WorkingDateTotalRecord> findByMonth(YearMonth yearMonth);
}
