package domain;

import api.TimeGetter;

public class Domain {

    public void registryKintai(DateVO dateVO, TimeVO timeVO, TimeGetter timeGetter, DatasourceRepository datasourceRepository) {
        datasourceRepository.writeData(dateVO, timeVO, timeGetter);
    }

    public void displayMonthWorkTime(DateVO dateVO, DatasourceRepository datasourceRepository) {
        datasourceRepository.readData(dateVO);
    }

}