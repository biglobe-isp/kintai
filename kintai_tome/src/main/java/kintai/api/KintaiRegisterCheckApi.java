package kintai.api;


import kintai.domain.company.KintaiCheckStatus;
import kintai.service.KintaiRegisterCheckService;

import java.io.IOException;

public class KintaiRegisterCheckApi {
    public KintaiCheckStatus check(KintaiRegisterCheckRequest kintaiRegisterCheckRequest) throws IOException {
        KintaiRegisterCheckService kintaiRegisterCheckService = new KintaiRegisterCheckService();

        return kintaiRegisterCheckService.check(
                kintaiRegisterCheckRequest.getArg1(),
                kintaiRegisterCheckRequest.getArg2(),
                kintaiRegisterCheckRequest.getArg3()
        );
    }
}
