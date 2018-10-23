package domain;

import domain.japan.*;

public interface DatasourceRepository {
    void writeKintai (WorkDateVO workDateVO, StartTime startTime, EndTime endTime, WorkTimeMinuteVO workTimeMinuteVO,
                      OverWorkTimeMinuteVO overWorkTimeMinuteVO, TimeGetterRepository timeGetterRepository);
    void readKintai (WorkYearMonthVO workYearMonthVO);
}
