package jp.co.biglobe.kintai.service;

import jp.co.biglobe.kintai.datasource.WorkTimeRepositoryDb;
import jp.co.biglobe.kintai.domain.*;

public class KintaiService {


    private static WorkTimeRepository workTimeRepository = new WorkTimeRepositoryDb();

    public static void input(String date, String start, String end, String now) {
        workTimeRepository.input(WorkingRule.getInstance().calculateWorkTime(new WorkDate(date),
                new StartWorkTime(start),
                new EndWorkTime(end),
                now
        ));

    }

    public static void total(String yearMonth) {
        workTimeRepository.findWorkTimeCard(yearMonth).ifPresent(timeCard -> {
                    System.out.println("勤務時間: " + timeCard.getTotalMinutes() / 60 + "時間" + timeCard.getTotalMinutes() % 60 + "分");
                    System.out.println("残業時間: " + timeCard.getTotalOverWorkMinutes() / 60 + "時間" + timeCard.getTotalOverWorkMinutes() % 60 + "分");
                }
        );
    }
}