package api;

import service.KintaiRegisterService;

public class KintaiRegitsterApi {
    private KintaiRegisterService kintaiRegisterService;

    public void kintaRegisterApi(KintaiRegisterRequest request) {
        kintaiRegisterService = new KintaiRegisterService();
        kintaiRegisterService.kintaiRegisterService(
                request.getWorkDay(),
                request.getWorkStartTime(),
                request.getWorkEndTime()
        );
    }
}