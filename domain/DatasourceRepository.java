package domain;

import japan.EndTime;
import japan.StartTime;

public interface DatasourceRepository {
    void writeKintai (WorkDateVO workDateVO, StartTime startTime, EndTime endTime, WorkTimeMinuteVO workTimeMinuteVO,
                      OverWorkTimeMinuteVO overWorkTimeMinuteVO, TimeGetterRepository timeGetterRepository);
    void readKintai (WorkYearMonthVO workYearMonthVO);
}
