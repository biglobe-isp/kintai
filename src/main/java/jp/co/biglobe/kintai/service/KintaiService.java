package jp.co.biglobe.kintai.service;

import jp.co.biglobe.kintai.datasource.TimeCardRepositoryDb;
import jp.co.biglobe.kintai.datasource.WorkTimeRepositoryDb;
import jp.co.biglobe.kintai.domain.*;

import java.util.Optional;

public class KintaiService {

    private WorkTimeRepository workTimeRepository;

    private TimeCardRepository timeCardRepository;

    public KintaiService() {
        this(new WorkTimeRepositoryDb(), new TimeCardRepositoryDb());
    }

    public KintaiService(WorkTimeRepository workTimeRepository, TimeCardRepository timeCardRepository) {
        this.workTimeRepository = workTimeRepository;
        this.timeCardRepository = timeCardRepository;
    }

    public Optional<MonthlyWorkTimeCard> total(YearMonth yearMonth) {
        return timeCardRepository.findWorkTimeCard(yearMonth);
    }

    public void input(WorkTime workTime) {
        workTimeRepository.input(WorkingRule.getInstance().calculateWorkTime(workTime));
    }
}