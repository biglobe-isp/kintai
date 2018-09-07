package kintai.service;

import kintai.datasource.KintaiRepositoryEvent;
import kintai.domain.HiKintai;

import java.io.IOException;
import java.util.List;

public class KintaiAggregateService {

    public List<HiKintai> refer(String yearMonth) throws IOException {

        KintaiRepositoryEvent kintaiRepositoryEvent = new KintaiRepositoryEvent();

        return kintaiRepositoryEvent.refer(yearMonth);

    }

}
