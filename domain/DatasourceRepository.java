package domain;

import api.TimeGetter;

public interface DatasourceRepository {
    void writeData (WorkDateVO workDateVO, StartTimeVO startTimeVO, EndTimeVO endTimeVO, TimeGetter timeGetter);
    void readData (WorkYearMonthVO workYearMonthVO);
}
