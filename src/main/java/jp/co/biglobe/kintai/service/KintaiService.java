package jp.co.biglobe.kintai.service;

import jp.co.biglobe.kintai.datasource.WorkTimeRepositoryDb;
import jp.co.biglobe.kintai.domain.*;

public class KintaiService {


    private WorkTimeRepository workTimeRepository;

    public KintaiService(){
        this(new WorkTimeRepositoryDb());
    }

    public KintaiService(WorkTimeRepository repository){
        workTimeRepository = repository;
    }

    public void input(String date, String start, String end, String now) {
        workTimeRepository.input(WorkingRule.getInstance().calculateWorkTime(new WorkDate(date),
                new StartWorkTime(start),
                new EndWorkTime(end),
                now
        ));

    }

    // yearMonthはバリュー
    public void total(String yearMonth) {
        workTimeRepository.findWorkTimeCard(yearMonth).ifPresent(timeCard -> {
            //タイムカードで時間と分の区分けを
            // 表示処理もApi層でここではTimeCardのみを返すように
                    System.out.println("勤務時間: " + timeCard.getTotalMinutes() / 60 + "時間" + timeCard.getTotalMinutes() % 60 + "分");
                    System.out.println("残業時間: " + timeCard.getTotalOverWorkMinutes() / 60 + "時間" + timeCard.getTotalOverWorkMinutes() % 60 + "分");
                }
        );
    }
}