package kintai.domain.shugyo_kisoku;

import kintai.domain.kintai.KinmuJikan;
import kintai.domain.kintai.KyukeiJikan;
import kintai.domain.kintai.KyukeiShuryoJikoku;
import kintai.domain.kintai.ShukkinJikoku;
import kintai.domain.kintai.TaikinJikoku;
import kintai.domain.kintai.ZaishaJikan;
import kintai.domain.kintai.ZangyoJikan;
import kintai.domain.shugyo_kisoku.kinmu_jikan_kisoku.KinmuJikanKisoku;
import kintai.domain.shugyo_kisoku.kyukei_jikan_kisoku.KiteiKyukeiJikan;
import kintai.domain.shugyo_kisoku.kyukei_jikan_kisoku.KiteiKyukeiKaishiJikoku;
import kintai.domain.shugyo_kisoku.kyukei_jikan_kisoku.KiteiKyukeiShuryoJikoku;
import kintai.domain.shugyo_kisoku.kyukei_jikan_kisoku.KyukeiJikanKisoku;

public class ShugyoKisoku {

    private KinmuJikanKisoku kinmuJikanKisoku;
    private KyukeiJikanKisoku kyukeiJikanKisoku;
    private Shikobi shikobi;

    public ShugyoKisoku(KyukeiJikanKisoku kyukeiJikanKisoku, KinmuJikanKisoku kinmuJikanKisoku, Shikobi shikobi) {
        this.setKinmuJikanKisoku(kinmuJikanKisoku);
        this.setKyukeiJikanKisoku(kyukeiJikanKisoku);
        this.setShikobi(shikobi);
    }

    public KinmuJikan calculateKinmuJikan(ShukkinJikoku shukkinJikoku, TaikinJikoku taikinJikoku) {
        ZaishaJikan zaishaJikan = new ZaishaJikan(shukkinJikoku, taikinJikoku);

        for (KiteiKyukeiJikan kiteiKyukeiJikan : this.kyukeiJikanKisoku.getKiteiKyukeiJikanList()) {
            KiteiKyukeiKaishiJikoku kiteiKyukeiKaishiJikoku = kiteiKyukeiJikan.getKiteiKyukeiKaishiJikoku();
            KiteiKyukeiShuryoJikoku kiteiKyukeiShuryoJikoku = kiteiKyukeiJikan.getKiteiKyukeiShuryoJikoku();

            if (kiteiKyukeiKaishiJikoku.isBefore(taikinJikoku)) {
                KyukeiJikan kyukeiJikan;

                if (kiteiKyukeiShuryoJikoku.isBefore(taikinJikoku)) {
                    kyukeiJikan = new KyukeiJikan(kiteiKyukeiKaishiJikoku.asKyukeiKaishiJikoku(),
                            kiteiKyukeiShuryoJikoku.asKyukeShuryoJikoku());
                } else {
                    kyukeiJikan = new KyukeiJikan(kiteiKyukeiKaishiJikoku.asKyukeiKaishiJikoku(),
                            new KyukeiShuryoJikoku(taikinJikoku));
                }

                zaishaJikan = zaishaJikan.minus(kyukeiJikan);
            }

        }

        return zaishaJikan.asKinmuJikan();
    }

    public ZangyoJikan calculateZangyoJikan(ShukkinJikoku shukkinJikoku, TaikinJikoku taikinJikoku) {
        return new ZangyoJikan(this.calculateKinmuJikan(shukkinJikoku, taikinJikoku), this.calculateTsujoKinmuJikan());
    }

    public KinmuJikan calculateTsujoKinmuJikan() {
        return this.calculateKinmuJikan(this.kinmuJikanKisoku.tsujoShukkinJikoku(),
                this.kinmuJikanKisoku.tsujoTaikinJikoku());
    }

    public KinmuJikan calculateKinmuJikanOfGozenkyu(TaikinJikoku taikinJikoku) {
        return this.calculateKinmuJikan(this.shukkinJikokuOfYukyu(), taikinJikoku);
    }

    public KinmuJikan calculateKinmuJikanOfGogokyu(ShukkinJikoku shukkinJikoku) {
        return this.calculateKinmuJikan(shukkinJikoku, this.taikinJikokuOfYukyu());
    }

    public KinmuJikan calculateKinmuJikanOfKyuka() {
        return this.calculateKinmuJikan(this.shukkinJikokuOfYukyu(), this.taikinJikokuOfYukyu());
    }

    public ZangyoJikan calculateZangyoJikanOfGozenkyu(TaikinJikoku taikinJikoku) {
        return this.calculateZangyoJikan(this.shukkinJikokuOfYukyu(), taikinJikoku);
    }

    public ZangyoJikan calculateZangyoJikanOfGogokyu(ShukkinJikoku shukkinJikoku) {
        return this.calculateZangyoJikan(shukkinJikoku, this.taikinJikokuOfYukyu());
    }

    public ZangyoJikan calculateZangyoJikanOfKyuka() {
        return this.calculateZangyoJikan(this.shukkinJikokuOfYukyu(), this.taikinJikokuOfYukyu());
    }

    public boolean isShikobiEqualsOrBefore(Shikobi shikobi) {
        return this.shikobi.isEqualsOrBefore(shikobi);
    }

    public ShukkinJikoku shukkinJikokuOfYukyu() {
        return this.kinmuJikanKisoku.tsujoShukkinJikoku();
    }

    public TaikinJikoku taikinJikokuOfYukyu() {
        return this.kinmuJikanKisoku.tsujoTaikinJikoku();
    }

    public KyukeiJikanKisoku getKyukeiJikanKisoku() {
        return this.kyukeiJikanKisoku;
    }

    private void setKyukeiJikanKisoku(KyukeiJikanKisoku kyukeiJikanKisoku) {
        this.kyukeiJikanKisoku = kyukeiJikanKisoku;
    }

    public KinmuJikanKisoku getKinmuJikanKisoku() {
        return this.kinmuJikanKisoku;
    }

    private void setKinmuJikanKisoku(KinmuJikanKisoku kinmuJikanKisoku) {
        this.kinmuJikanKisoku = kinmuJikanKisoku;
    }

    public Shikobi getShikobi() {
        return this.shikobi;
    }

    private void setShikobi(Shikobi shikobi) {
        this.shikobi = shikobi;
    }
}