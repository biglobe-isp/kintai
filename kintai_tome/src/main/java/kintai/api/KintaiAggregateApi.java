package kintai.api;

import kintai.domain.HiKintai;
import kintai.service.KintaiAggregateService;

import java.io.IOException;
import java.util.List;

public class KintaiAggregateApi {
    public List<HiKintai> refer(KintaiAggregateRequest kintaiAggregateRequest) throws IOException {
        KintaiAggregateService kintaiAggregateService = new KintaiAggregateService();

        return kintaiAggregateService.refer(kintaiAggregateRequest.getArg1());

    }
}
