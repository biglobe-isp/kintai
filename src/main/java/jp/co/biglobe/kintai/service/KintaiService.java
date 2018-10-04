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

    public Optional<MonthlyWorkTimeCard> total(YearMonth yearMonth) {
        return workTimeRepository.findWorkTimeCard(yearMonth);
    }

    public void input(WorkTime workTime) {
        workTimeRepository.input(WorkingRule.getInstance().calculateWorkTime(workTime));
    }
}