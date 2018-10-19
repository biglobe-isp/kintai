package service;

import api.TimeGetter;
import domain.*;

/**
 * Service層のメインの処理を担うクラス。
 */
public class Service {

    public void registryKintai(WorkDateVO workDateVO, StartTimeVO startTimeVO, EndTimeVO endTimeVO, TimeGetter timeGetter, DatasourceRepository datasourceRepository) {
        Domain dm = new Domain();
        dm.registryKintai(workDateVO, startTimeVO, endTimeVO, timeGetter, datasourceRepository);
    }

    public void displayMonthWorkTime(WorkYearMonthVO workYearMonthVO, DatasourceRepository datasourceRepository) {
        Domain dm = new Domain();
        dm.displayMonthWorkTime(workYearMonthVO, datasourceRepository);
    }
}