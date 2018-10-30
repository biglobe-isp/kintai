package jp.co.biglobe.kintai.service;

import jp.co.biglobe.kintai.datasource.BreakTimeRepositoryDb;
import jp.co.biglobe.kintai.datasource.TimeCardRepositoryDb;
import jp.co.biglobe.kintai.datasource.WorkTimeRepositoryDb;
import jp.co.biglobe.kintai.domain.BreakTimeRepository;
import jp.co.biglobe.kintai.domain.MonthlyWorkTimeCard;
import jp.co.biglobe.kintai.domain.TimeCardRepository;
import jp.co.biglobe.kintai.domain.WorkTime;
import jp.co.biglobe.kintai.domain.WorkTimeRepository;
import jp.co.biglobe.kintai.domain.WorkingRule;
import jp.co.biglobe.kintai.domain.YearMonth;
import jp.co.biglobe.kintai.domain.breaktime.DailyBreakTimes;

import java.util.Optional;

public class KintaiService {

    private WorkTimeRepository workTimeRepository;

    private TimeCardRepository timeCardRepository;

    private BreakTimeRepository breakTimeRepository;

    public KintaiService() {
        this(new WorkTimeRepositoryDb(), new TimeCardRepositoryDb(), new BreakTimeRepositoryDb());
    }

    public KintaiService(
            WorkTimeRepository workTimeRepository, TimeCardRepository timeCardRepository,
            BreakTimeRepository breakTimeRepository) {
        this.workTimeRepository = workTimeRepository;
        this.timeCardRepository = timeCardRepository;
        this.breakTimeRepository = breakTimeRepository;
    }

    public Optional<MonthlyWorkTimeCard> total(YearMonth yearMonth) {
        return timeCardRepository.findWorkTimeCard(yearMonth);
    }

    public void input(WorkTime workTime) {
        final DailyBreakTimes breakTimes = breakTimeRepository.findBreakTimes();
        workTimeRepository.input(WorkingRule.getInstance().calculateWorkTime(workTime, breakTimes));
    }
}