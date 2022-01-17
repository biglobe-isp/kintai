package service;

import domain.entity.WorkRecord;
import domain.repository.WorkRecordRepositoryInterface;
import domain.value.*;
import lombok.NonNull;

import java.util.Collection;

public final class WorkRecordApplicationService {
    private final WorkRecordRepositoryInterface workRecordRepository;

    public WorkRecordApplicationService(@NonNull WorkRecordRepositoryInterface workRecordRepository) {
        this.workRecordRepository = workRecordRepository;
    }

    public void inputWorkRecord(int year, int month, int day, int startHour, int startMinutes, int endHour, int endMinutes) {
        WorkRecord workRecord = new WorkRecord(
                new WorkDate(year, month, day),
                new WorkTime(startHour, startMinutes, endHour, endMinutes)
        );
        this.workRecordRepository.save(workRecord);
    }

    public TotalWorkMinutesModel computeTotalWorkMinutes(int year, int month) {
        Collection<WorkRecord> workRecords = this.workRecordRepository.findByYearAndMonth(new WorkYearMonth(year, month));
        return new TotalWorkMinutesModel(
                workRecords
                        .stream()
                        .mapToInt(workRecord -> workRecord.computeWorkMinutes().getValue())
                        .sum(),
                workRecords
                        .stream()
                        .mapToInt(workRecord -> workRecord.computeOverWorkMinutes().getValue())
                        .sum()
        );
    }
}
