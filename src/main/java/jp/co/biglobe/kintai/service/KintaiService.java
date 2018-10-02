package jp.co.biglobe.kintai.service;

import jp.co.biglobe.kintai.datasource.WorkTimeRepositoryDb;
import jp.co.biglobe.kintai.domain.*;

import java.util.Optional;

public class KintaiService {


    private WorkTimeRepository workTimeRepository;

    public KintaiService(){
        this(new WorkTimeRepositoryDb());
    }

    public KintaiService(WorkTimeRepository repository){
        workTimeRepository = repository;
    }

    // yearMonthはバリュー
    public Optional<MonthlyWorkTimeCard> total(YearMonth yearMonth) {
        return workTimeRepository.findWorkTimeCard(yearMonth);
    }

    public void input(WorkDate workDate, StartWorkTime startTime, EndWorkTime endTime, NowTime nowTime) {
        workTimeRepository.input(WorkingRule.getInstance().calculateWorkTime(
                workDate,
                startTime,
                endTime,
                nowTime
        ));
    }
}