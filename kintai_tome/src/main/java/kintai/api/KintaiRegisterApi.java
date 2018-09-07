package kintai.api;


import kintai.service.KintaiRegisterService;

import java.io.IOException;

public class KintaiRegisterApi {
    public void register(KintaiRegisterRequest kintaiRegisterRequest) throws IOException {
        KintaiRegisterService kintaiRegisterService = new KintaiRegisterService();

        kintaiRegisterService.register(
                kintaiRegisterRequest.getArg1(),
                kintaiRegisterRequest.getArg2(),
                kintaiRegisterRequest.getArg3()
        );
    }
}
