package api;

import service.KintaiRegisterService;

public class KintaiRegitsterApi {

    public static void kintaRegisterApi(KintaiRegisterRequest request) {
        KintaiRegisterService kintaiService = new KintaiRegisterService();
        kintaiService.kintaiRegisterService(
                request.getWorkDay(),
                request.getWorkStartTime(),
                request.getWorkEndTime()
        );
    }
}