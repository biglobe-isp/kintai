package kintai.domain.kintai;

import java.time.Duration;
import java.util.HashMap;

public class KintaiShukeiService {
    private KintaiRepository kintaiRepository;

    public KintaiShukeiService (
        KintaiRepository kintaiRepository
    ) {
        this.kintaiRepository = kintaiRepository;
    }

    public KinmuJikan shukeiKinmuJikan(Getsudo getsudo) {
        HashMap<String, Kintai> kintais = this.kintaiRepository.KintaisOfGetsudo(getsudo);

        KinmuJikan kinmuJikan = KinmuJikan.zeroKinmuJikan();

        for(Kintai kintai: kintais.values()) {
            kinmuJikan = kinmuJikan.plus(kintai.getKinmuJikan());
        }

        return kinmuJikan;
    }

    public ZangyoJikan shukeiZangyoJikan(Getsudo getsudo) {        
        HashMap<String, Kintai> kintais = this.kintaiRepository.KintaisOfGetsudo(getsudo);

        ZangyoJikan zangyoJikan = ZangyoJikan.zeroZangyoJikan();

        for(Kintai kintai: kintais.values()) {
            zangyoJikan = zangyoJikan.plus(kintai.getZangyoJikan());
        }

        return zangyoJikan;
    }
}