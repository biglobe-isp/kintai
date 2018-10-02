package jp.co.biglobe.kintai.api;

import jp.co.biglobe.kintai.service.KintaiService;

public class KintaiApi {

    private KintaiService kintaiService;

    public KintaiApi(){
        this(new KintaiService());
    }

    public KintaiApi(KintaiService service){
        kintaiService = service;
    }

    public void input(String date, String start, String end, String now) {
        //クラスの変換
        // valueオブジェクトへの変換で綺麗なデータに
        kintaiService.input(date, start, end, now);
    }

    public void total(String yearMonth) {
        kintaiService.total(yearMonth);
    }
}
