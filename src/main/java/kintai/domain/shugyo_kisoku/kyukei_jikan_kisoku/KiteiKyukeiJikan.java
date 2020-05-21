package kintai.domain.shugyo_kisoku.kyukei_jikan_kisoku;

import java.time.Duration;

public class KiteiKyukeiJikan {
    private KiteiKyukeiKaishiJikoku kiteiKyukeiKaishiJikoku;
    private KiteiKyukeiShuryoJikoku kiteiKyukeiShuryoJikoku;

    public KiteiKyukeiJikan(KiteiKyukeiKaishiJikoku kiteiKyukeiKaishiJikoku, KiteiKyukeiShuryoJikoku kiteiKyukeiShuryoJikoku) {
        this.setKiteiKyukeiKaishiJikoku(kiteiKyukeiKaishiJikoku);
        this.setKiteiKyukeiShuryoJikoku(kiteiKyukeiShuryoJikoku);
    }

    public KiteiKyukeiKaishiJikoku getKiteiKyukeiKaishiJikoku() {
        return this.kiteiKyukeiKaishiJikoku;
    }

    private void setKiteiKyukeiKaishiJikoku(KiteiKyukeiKaishiJikoku kiteiKyukeiKaishiJikoku) {
        this.kiteiKyukeiKaishiJikoku = kiteiKyukeiKaishiJikoku;
    }

    public KiteiKyukeiShuryoJikoku getKiteiKyukeiShuryoJikoku() {
        return this.kiteiKyukeiShuryoJikoku;
    }

    private void setKiteiKyukeiShuryoJikoku(KiteiKyukeiShuryoJikoku kiteiKyukeiShuryoJikoku) {
        this.kiteiKyukeiShuryoJikoku = kiteiKyukeiShuryoJikoku;
    }

    public Duration kiteiKyukeiJikan() {
        return Duration.between(this.kiteiKyukeiKaishiJikoku.getKiteiKyukeiKaishiJikoku(),
                this.kiteiKyukeiShuryoJikoku.getKiteiKyukeiShuryoJikoku());
    }
}