package kintai.service;

import java.time.LocalDate;

import kintai.domain.kintai.Getsudo;
import kintai.domain.kintai.KinmuJikan;
import kintai.domain.kintai.KintaiRepository;
import kintai.domain.kintai.KintaiShukeiService;
import kintai.domain.kintai.ShukeiKekka;
import kintai.domain.kintai.ZangyoJikan;
import kintai.domain.util.Clock;

public class ShukeiService {
    private KintaiRepository kintaiRepository;
    private Clock clock;

    public ShukeiService(KintaiRepository kintaiRepository, Clock clock) {
        this.kintaiRepository = kintaiRepository;
        this.clock = clock;
    }

    public ShukeiKekka handle() {
        KintaiShukeiService kintaiShukeiService = new KintaiShukeiService(this.kintaiRepository);
        LocalDate today = LocalDate.of(this.clock.now().getYear(), this.clock.now().getMonth(), this.clock.now().getDayOfMonth());
        Getsudo getsudo = Getsudo.getsudoOfTheDate(today);

        KinmuJikan shukeiKinmuJikan = kintaiShukeiService.shukeiKinmuJikan(getsudo);
        ZangyoJikan shukeiZangyoJikan = kintaiShukeiService.shukeiZangyoJikan(getsudo);

        return new ShukeiKekka(shukeiKinmuJikan, shukeiZangyoJikan);
    }
}