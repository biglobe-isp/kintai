package kintai.domain.shugyo_kisoku.kyukei_jikan_kisoku;

import java.time.LocalTime;

import kintai.domain.kintai.KyukeiKaishiJikoku;
import kintai.domain.kintai.TaikinJikoku;

public class KiteiKyukeiKaishiJikoku {
    private LocalTime kiteiKyukeiKaishiJikoku;

    public KiteiKyukeiKaishiJikoku(LocalTime kiteiKyukeiKaishiJikoku) {
        this.setKiteiKyukeiKaishiJikoku(kiteiKyukeiKaishiJikoku);
    }

    public LocalTime getKiteiKyukeiKaishiJikoku() {
        return this.kiteiKyukeiKaishiJikoku;
    }

    private void setKiteiKyukeiKaishiJikoku(LocalTime kiteiKyukeiKaishiJikoku) {
        this.kiteiKyukeiKaishiJikoku = kiteiKyukeiKaishiJikoku;
    }

    public boolean isBefore(TaikinJikoku taikinJikoku) {
        return this.kiteiKyukeiKaishiJikoku.isBefore(taikinJikoku.getTaikinJikoku());
    }

    public KyukeiKaishiJikoku asKyukeiKaishiJikoku() {
        return new KyukeiKaishiJikoku(this.kiteiKyukeiKaishiJikoku);
    }
}