package jp.co.biglobe.kintai.api;

import jp.co.biglobe.kintai.domain.*;
import jp.co.biglobe.kintai.service.KintaiService;
import jp.co.biglobe.kintai.util.StringUtil;

public class KintaiApi {

    private KintaiService kintaiService;

    public KintaiApi() {
        this(new KintaiService());
    }

    public KintaiApi(KintaiService service) {
        kintaiService = service;
    }

    public void input(String date, String start, String end, String now) {
        WorkDate workDate;
        if (StringUtil.isNullorEmpty(date)) {
            throw new RuntimeException("データに不備があります。");
        } else {
            workDate = new WorkDate(date);
        }

        StartWorkTime startTime;
        if (StringUtil.isNullorEmpty(start)) {
            throw new RuntimeException("データに不備があります。");
        }
        startTime = new StartWorkTime(start);

        EndWorkTime endTime;
        if (StringUtil.isNullorEmpty(end)) {
            throw new RuntimeException("データに不備があります。");
        }
        endTime = new EndWorkTime(end);

        NowTime nowTime;
        if(StringUtil.isNullorEmpty((now))){
            throw new RuntimeException("データに不備があります。");
        }
        nowTime = new NowTime(now);

        kintaiService.input(workDate, startTime, endTime, nowTime);

    }

    public void total(String yearMonth) {
        YearMonth month;
        if(StringUtil.isNullorEmpty(yearMonth)){
            throw new RuntimeException("データに不備があります。");
        }
        month = new YearMonth(yearMonth);

        kintaiService.total(month).ifPresent(timeCard -> {
                    //タイムカードで時間と分の区分けを
                    System.out.println("勤務時間: " + timeCard.getTotalMinutes() / 60 + "時間" + timeCard.getTotalMinutes() % 60 + "分");
                    System.out.println("残業時間: " + timeCard.getTotalOverWorkMinutes() / 60 + "時間" + timeCard.getTotalOverWorkMinutes() % 60 + "分");
                }
        );
    }
}
