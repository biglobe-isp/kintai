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


        WorkTime workTime = new WorkTime(
                workDate,
                startTime,
                endTime,
                0,
                0,
                nowTime
        );

        kintaiService.input(workTime);
    }

    public void total(String yearMonth) {
        YearMonth month;
        if(StringUtil.isNullorEmpty(yearMonth)){
            throw new RuntimeException("データに不備があります。");
        }
        month = new YearMonth(yearMonth);

        kintaiService.total(month).ifPresent(timeCard -> {
                    System.out.println("勤務時間: " + timeCard.getWorkHours() + "時間" + timeCard.getWorkMinutes() + "分");
                    System.out.println("残業時間: " + timeCard.getOverWorkHours() + "時間" + timeCard.getOverWorkMinutes() + "分");
                }
        );
    }
}
