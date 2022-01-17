package domain.repository;

import domain.entity.WorkRecord;
import domain.value.WorkYearMonth;
import lombok.NonNull;
import java.util.Collection;

public interface WorkRecordRepositoryInterface {
    void save(@NonNull WorkRecord workRecord);

    Collection<WorkRecord> findByYearAndMonth(@NonNull WorkYearMonth workYearMonth);
}
