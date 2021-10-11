package api;

import domain.TotallyMonth;
import org.springframework.beans.factory.annotation.Autowired;
import service.KintaiCountUpService;

public class KintaiCountUpApi {
    @Autowired
    KintaiCountUpRequest kintaiCountUpRequest;
    public static void coutupApi(KintaiCountUpRequest kintaiCountUpRequest) {
        KintaiCountUpService kintaiService = new KintaiCountUpService();
        kintaiService.kintaiCountUpService(kintaiCountUpRequest.getValueObject());
    }
}
