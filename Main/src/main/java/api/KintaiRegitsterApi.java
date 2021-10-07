package api;

import service.KintaiRegisterService;

public class KintaiRegitsterApi {

    public static void kintaRegisterApi(String[] args) {
        KintaiRegisterRequest kintaiRegisterRequest = new KintaiRegisterRequest(args);
        KintaiRegisterService kintaiService = new KintaiRegisterService();
        kintaiService.kintaiRegisterService(
                kintaiRegisterRequest.getWorkDay(),
                kintaiRegisterRequest.getWorkStartTime(),
                kintaiRegisterRequest.getWorkEndTime()
        );
    }
}