package kintai.datasource;

import kintai.domain.WorkingDateTotalRecord;
import kintai.service.WorkingDateTotalRecordRepository;
import lombok.RequiredArgsConstructor;

import java.time.YearMonth;
import java.util.List;

@RequiredArgsConstructor
public class WorkingDateTotalRecordRepositoryDb implements WorkingDateTotalRecordRepository {

    @Override
    public void save(WorkingDateTotalRecord workingDateTotalRecord) {

    }

    @Override
    public List<WorkingDateTotalRecord> findByMonth(YearMonth yearMonth) {
        return null;
    }
}
