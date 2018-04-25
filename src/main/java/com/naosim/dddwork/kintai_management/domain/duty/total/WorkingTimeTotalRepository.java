package com.naosim.dddwork.kintai_management.domain.duty.total;


import java.util.Optional;

/**
 *
 */
public interface WorkingTimeTotalRepository {

        Optional<WorkingTimeTotalResult> totalWorkingTime(WorkingTimeTotalInput workingTimeTotalInput);
}
