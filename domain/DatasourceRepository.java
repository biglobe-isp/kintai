package domain;

import api.TimeGetter;

public interface DatasourceRepository {
    void writeData (DateVO dateVO, TimeVO timeVO, TimeGetter timeGetter);
    void readData (DateVO dateVO);
}
