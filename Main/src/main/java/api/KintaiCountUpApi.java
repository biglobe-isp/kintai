package api;

import service.KintaiCountUpService;

public class KintaiCountUpApi {
    public static void coutupApi(String[] args) {
        KintaiCountUpRequest kintaiCountUpRequest = new KintaiCountUpRequest(args);
        KintaiCountUpService kintaiService = new KintaiCountUpService();
        kintaiService.kintaiCountUpService(kintaiCountUpRequest.getValueObject());
    }
}
