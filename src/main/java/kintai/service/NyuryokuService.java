package kintai.service;

import kintai.domain.kintai.Kinmubi;
import kintai.domain.kintai.Kintai;
import kintai.domain.kintai.KintaiRepository;
import kintai.domain.kintai.ShukkinJikoku;
import kintai.domain.kintai.TaikinJikoku;
import kintai.domain.shugyo_kisoku.ShugyoKisoku;
import kintai.domain.shugyo_kisoku.ShugyoKisokuRepository;
import kintai.domain.util.Clock;

public class NyuryokuService {
    private KintaiRepository kintaiRepository;
    private ShugyoKisokuRepository shugyoKisokuRepository;
    private Clock clock;

    public NyuryokuService(KintaiRepository kintaiRepository, ShugyoKisokuRepository shugyoKisokuRepository,
            Clock clock) {
        this.kintaiRepository = kintaiRepository;
        this.shugyoKisokuRepository = shugyoKisokuRepository;
        this.clock = clock;
    }

    /**
     * 通常
     * 
     * @param kinmubi
     * @param shukkinJikoku
     * @param taikinJikoku
     */
    public void handle(Kinmubi kinmubi, ShukkinJikoku shukkinJikoku, TaikinJikoku taikinJikoku) {
        ShugyoKisoku shugyoKisoku = this.shugyoKisokuRepository.shugyoKisokuOfKinmubi(kinmubi);
        Kintai kintai = Kintai.nyuryoku(kinmubi, shukkinJikoku, taikinJikoku,
                shugyoKisoku.calculateKinmuJikan(shukkinJikoku, taikinJikoku),
                shugyoKisoku.calculateZangyoJikan(shukkinJikoku, taikinJikoku), this.clock);
        this.kintaiRepository.store(kintai);
    }

    /**
     * 午前休
     * 
     * @param kinmubi
     * @param taikinJikoku
     */
    public void handle(Kinmubi kinmubi, TaikinJikoku taikinJikoku) {
        ShugyoKisoku shugyoKisoku = this.shugyoKisokuRepository.shugyoKisokuOfKinmubi(kinmubi);

        Kintai kintai = Kintai.nyuryoku(kinmubi, shugyoKisoku.shukkinJikokuOfYukyu(), taikinJikoku,
                shugyoKisoku.calculateKinmuJikanOfGozenkyu(taikinJikoku),
                shugyoKisoku.calculateZangyoJikanOfGozenkyu(taikinJikoku), this.clock);
        this.kintaiRepository.store(kintai);
    }

    /**
     * 午後休
     * 
     * @param kinmubi
     * @param shukkinJikoku
     */
    public void handle(Kinmubi kinmubi, ShukkinJikoku shukkinJikoku) {
        ShugyoKisoku shugyoKisoku = this.shugyoKisokuRepository.shugyoKisokuOfKinmubi(kinmubi);

        Kintai kintai = Kintai.nyuryoku(kinmubi, shukkinJikoku, shugyoKisoku.taikinJikokuOfYukyu(),
                shugyoKisoku.calculateKinmuJikanOfGogokyu(shukkinJikoku),
                shugyoKisoku.calculateZangyoJikanOfGogokyu(shukkinJikoku), this.clock);
        this.kintaiRepository.store(kintai);
    }

    /**
     * 休暇
     * 
     * @param kinmubi
     */
    public void handle(Kinmubi kinmubi) {
        ShugyoKisoku shugyoKisoku = this.shugyoKisokuRepository.shugyoKisokuOfKinmubi(kinmubi);

        Kintai kintai = Kintai.nyuryoku(kinmubi, shugyoKisoku.shukkinJikokuOfYukyu(), shugyoKisoku.taikinJikokuOfYukyu(),
                shugyoKisoku.calculateKinmuJikanOfKyuka(),
                shugyoKisoku.calculateZangyoJikanOfKyuka(), this.clock);
        this.kintaiRepository.store(kintai);
    }
}
