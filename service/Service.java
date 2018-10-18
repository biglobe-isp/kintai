package service;

import api.TimeGetter;
import domain.*;

public class Service {

    public void registryKintai(DateVO dateVO, TimeVO timeVO, TimeGetter timeGetter, DatasourceRepository datasourceRepository) {
        Domain dm = new Domain();
        dm.registryKintai(dateVO, timeVO, timeGetter, datasourceRepository);
    }

    public void displayMonthWorkTime(DateVO dateVO, DatasourceRepository datasourceRepository) {
        Domain dm = new Domain();
        dm.displayMonthWorkTime(dateVO, datasourceRepository);
    }
}