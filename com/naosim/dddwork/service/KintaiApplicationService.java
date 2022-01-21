package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.IKintaiRepository;
import com.naosim.dddwork.domain.entity.Kintai;
import com.naosim.dddwork.domain.valueObject.KintaiDate;
import com.naosim.dddwork.domain.valueObject.KintaiEnd;
import com.naosim.dddwork.domain.valueObject.KintaiOverWorkMinutes;
import com.naosim.dddwork.domain.KintaiService;
import com.naosim.dddwork.domain.valueObject.KintaiStart;
import com.naosim.dddwork.domain.KintaiTotalModel;
import com.naosim.dddwork.domain.valueObject.KintaiWorkMinutes;

import java.util.Map;

//アプリケーションサービス
public class KintaiApplicationService {

    private IKintaiRepository kintaiRepository;
    private KintaiService kintaiService;

    public KintaiApplicationService(IKintaiRepository kintaiRepository) {
        this.kintaiRepository = kintaiRepository;
        kintaiService = new KintaiService(kintaiRepository);
    }

    public void registerKintai(String date, String start, String end) {
        Kintai kintai = new Kintai(
                new KintaiDate(date),
                new KintaiStart(start),
                new KintaiEnd(end)
        );
        kintaiRepository.save(kintai);
    }

    public KintaiTotalModel getTotalWorkTime(String yearMonth) {
        Map<KintaiDate, KintaiWorkMinutes> kintaiWorkMinutesMap = kintaiRepository.getTotalWorkMinutesMapOf(yearMonth);//リポジトリでデータを取ってくる
        Map<KintaiDate, KintaiOverWorkMinutes> kintaiOverWorkMinutesMap = kintaiRepository.getTotalOverWorkMinutesMapOf(yearMonth);

        int totalWorkMinutes = kintaiService.sumKintaiWorkMinutes(kintaiWorkMinutesMap);//加算したデータをとってくる。データを横断的に扱うので加算はドメインサービスにした（このクラスでやるべきか迷ったがアプリケーションサービスではデータの加工などはやっていなさそうだった。 ）
        int totalOverWorkMinutes = kintaiService.sumKintaiOverWorkMinutes(kintaiOverWorkMinutesMap);

        return new KintaiTotalModel(totalWorkMinutes, totalOverWorkMinutes);
    }

}
