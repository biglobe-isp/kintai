package kintai.service;

import kintai.datasource.KintaiRepositoryEvent;
import kintai.domain.HiKintai;
import kintai.domain.SyukkinDate;
import kintai.domain.SyukkinTime;
import kintai.domain.TaikinTime;

import java.io.IOException;
import java.util.List;

public class KintaiRegisterService {

    public void register(SyukkinDate date, SyukkinTime start, TaikinTime end) throws IOException {

        KintaiRepositoryEvent kintaiRepositoryEvent = new KintaiRepositoryEvent();

        HiKintai hiKintai = HiKintai.create(date.getValue(), start.getValue(), end.getValue());

        List<HiKintai> list = kintaiRepositoryEvent.refer(hiKintai.getSyukkinDate().getYearMonth());

        for (HiKintai kintai : list) {

            // 一致している行を更新
            if (kintai.getSyukkinDate().getValue().equals(date.getValue())) {

                kintaiRepositoryEvent.update(hiKintai);
                return;
            }
        }

        // 一致する行がなければ登録
        kintaiRepositoryEvent.register(hiKintai);
        return;

    }

}
