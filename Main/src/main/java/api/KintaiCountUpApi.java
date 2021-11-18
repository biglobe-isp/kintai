package api;

import service.KintaiCountUpService;

public class KintaiCountUpApi {
    KintaiCountUpService kintaiCountUpService;

    public void coutupApi(KintaiCountUpRequest kintaiCountUpRequest) {
        kintaiCountUpService.kintaiCountUpService(kintaiCountUpRequest.getValueObject());
    }
}
