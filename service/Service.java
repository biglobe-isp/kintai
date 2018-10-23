package service;

import domain.*;
import domain.japan.*;

/**
 * Service層のメインの処理を担うクラス。
 */
public class Service {

    public void registryKintai(WorkDateVO workDateVO, StartTime startTime, EndTime endTime,
                               WorkTimeMinuteVO workTimeMinuteVO, OverWorkTimeMinuteVO overWorkTimeMinuteVO,
                               TimeGetterRepository timeGetterRepository, DatasourceRepository datasourceRepository) {
        Domain dm = new Domain();
        dm.registryKintai(workDateVO, startTime, endTime, workTimeMinuteVO, overWorkTimeMinuteVO,
                            timeGetterRepository, datasourceRepository);
    }

    public void displayMonthWorkTime(WorkYearMonthVO workYearMonthVO, DatasourceRepository datasourceRepository) {
        Domain dm = new Domain();
        dm.displayMonthWorkTime(workYearMonthVO, datasourceRepository);
    }
}