package com.naosim.dddwork.domain;

import java.time.YearMonth;
import java.util.List;

public interface WorkDataRepository {
    void save(WorkTime workTime, WorkMinutes workMinutes, WorkMinutes overWorkMinutes);
    List<WorkTime> findMonthWorkTime(YearMonth yearMonth);
}
