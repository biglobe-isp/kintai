package jp.co.biglobe.kintai.api;

import jp.co.biglobe.kintai.service.KintaiService;

public class KintaiApi {


    public static void input(String date, String start, String end, String now){
        KintaiService.input(date,start,end,now);
    }

    public static void total(String yearMonth){
        KintaiService.total(yearMonth);
    }
}
