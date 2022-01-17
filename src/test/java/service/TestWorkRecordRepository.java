package service;

import domain.entity.WorkRecord;
import domain.repository.WorkRecordRepositoryInterface;
import domain.value.WorkDate;
import domain.value.WorkYearMonth;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

class TestWorkRecordRepository implements WorkRecordRepositoryInterface {
    private Map<WorkDate, WorkRecord> map = new HashMap<>();

    @Override
    public void save(WorkRecord workRecord) {
        map.put(workRecord.getWorkDate(), workRecord);
    }

    @Override
    public Collection<WorkRecord> findByYearAndMonth(WorkYearMonth workYearMonth) {
        return map.values()
                .stream()
                .filter(workRecord -> workRecord.getWorkDate().belongs(workYearMonth))
                .collect(Collectors.toList());
    }

}
