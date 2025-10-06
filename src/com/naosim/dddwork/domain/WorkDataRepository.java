package com.naosim.dddwork.domain;

import java.time.YearMonth;
import java.util.List;

public interface WorkDataRepository {
    void save(WorkTime workTime, WorkTimeStorage dailyWorkData);
    List<WorkTimeStorage> findMonthWorkTime(YearMonth yearMonth);
}
// StoreMonthに依存するような形にしてはいけない
// 合計勤務時間や残業時間も格納すると楽
