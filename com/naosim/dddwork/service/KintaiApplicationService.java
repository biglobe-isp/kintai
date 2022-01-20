package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.IKintaiRepository;
import com.naosim.dddwork.domain.Kintai;
import com.naosim.dddwork.domain.KintaiDate;
import com.naosim.dddwork.domain.KintaiEnd;
import com.naosim.dddwork.domain.KintaiService;
import com.naosim.dddwork.domain.KintaiStart;
import com.naosim.dddwork.domain.KintaiMapModel;
import com.naosim.dddwork.domain.KintaiTotalModel;

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
        KintaiMapModel kintaiMapModel = kintaiRepository.getTotalWorkTimeMapsOf(yearMonth);//リポジトリでデータを取ってくる
        KintaiTotalModel kintaiTotalModel = kintaiService.sumWorkTime(kintaiMapModel.getTotalWorkMinutesMap(), kintaiMapModel.getTotalOverWorkMinutesMap());//加算したデータをとってくる。データを横断的に扱うので加算はドメインサービスにした（このクラスでやるべきか迷ったがアプリケーションサービスではデータの加工などはやっていなさそうだった。 ）

        return kintaiTotalModel;
    }

}
