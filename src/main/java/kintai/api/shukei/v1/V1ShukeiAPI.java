package kintai.api.shukei.v1;

import kintai.api.shukei.ShukeiAPI;
import kintai.domain.kintai.ShukeiKekka;
import kintai.service.ShukeiService;

public class V1ShukeiAPI implements ShukeiAPI {
    private ShukeiService shukeiService;

    public V1ShukeiAPI(ShukeiService shukeiService) {
        this.shukeiService = shukeiService;
    }

    public void handle(String[] args) {
        ShukeiKekka shukeiKekka = this.shukeiService.handle();
        
        long shukeiKinmuJikanSeconds = shukeiKekka.getShukeiKinmuJikan().getKinmuJikan().getSeconds();
        System.out.println(String.format("勤務時間:%d時間%d分", shukeiKinmuJikanSeconds / 3600, (shukeiKinmuJikanSeconds % 3600) / 60));

        long shukeiZangyoJikanSeconds = shukeiKekka.getShukeiZangyoJikan().getZangyoJikan().getSeconds();
        System.out.println(String.format("残業時間:%d時間%d分", shukeiZangyoJikanSeconds / 3600, (shukeiZangyoJikanSeconds % 3600) / 60));
    }
}