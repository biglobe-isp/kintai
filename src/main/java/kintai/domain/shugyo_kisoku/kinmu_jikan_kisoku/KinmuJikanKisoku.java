package kintai.domain.shugyo_kisoku.kinmu_jikan_kisoku;

import kintai.domain.kintai.ShukkinJikoku;
import kintai.domain.kintai.TaikinJikoku;
import kintai.domain.kintai.ZaishaJikan;

public class KinmuJikanKisoku {
    private KiteiKinmuJikan kiteiKinmuJikan;

    public KinmuJikanKisoku(KiteiKinmuJikan kiteiKinmuJikan) {
        this.setKiteiKinmuJikan(kiteiKinmuJikan);
    }

    public KiteiKinmuJikan getKiteiKinmuJikan() {
        return this.kiteiKinmuJikan;
    }

    public void setKiteiKinmuJikan(KiteiKinmuJikan kiteiKinmuJikan) {
        this.kiteiKinmuJikan = kiteiKinmuJikan;
    }

    public ZaishaJikan calculateTsujoZaishaJikan() {
        return this.kiteiKinmuJikan.calculateTsujoZaishaJikan();
    }

    public ShukkinJikoku tsujoShukkinJikoku() {
        return this.kiteiKinmuJikan.tsujoShukkinJikoku();
    }

    public TaikinJikoku tsujoTaikinJikoku() {
        return this.kiteiKinmuJikan.tsujoTaikinJikoku();
    }
}
