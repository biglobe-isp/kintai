package domain;

import api.TimeGetter;

/**
 * Domain層のメインの処理を担うクラス。
 */
public class Domain {

    public void registryKintai(WorkDateVO workDateVO, StartTimeVO startTimeVO, EndTimeVO endTimeVO, TimeGetter timeGetter, DatasourceRepository datasourceRepository) {
        datasourceRepository.writeData(workDateVO, startTimeVO, endTimeVO, timeGetter);
    }

    public void displayMonthWorkTime(WorkYearMonthVO workYearMonthVO, DatasourceRepository datasourceRepository) {
        datasourceRepository.readData(workYearMonthVO);
    }

}