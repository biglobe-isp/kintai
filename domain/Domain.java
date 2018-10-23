package domain;

import domain.japan.*;

/**
 * Domain層のメインの処理を担うクラス。
 */
public class Domain {

    public void registryKintai(WorkDateVO workDateVO, StartTime startTime, EndTime endTime,
                               WorkTimeMinuteVO workTimeMinuteVO, OverWorkTimeMinuteVO overWorkTimeMinuteVO,
                               TimeGetterRepository timeGetterRepository, DatasourceRepository datasourceRepository) {

        datasourceRepository.writeKintai(workDateVO, startTime, endTime, workTimeMinuteVO, overWorkTimeMinuteVO,
                                            timeGetterRepository);
    }

    public void displayMonthWorkTime(WorkYearMonthVO workYearMonthVO, DatasourceRepository datasourceRepository) {
        datasourceRepository.readKintai(workYearMonthVO);
    }

}