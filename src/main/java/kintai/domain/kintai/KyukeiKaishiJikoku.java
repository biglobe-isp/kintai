package kintai.domain.kintai;

import java.time.LocalTime;

public class KyukeiKaishiJikoku {
    private LocalTime kyukeiKaishiJikoku;

    public KyukeiKaishiJikoku(LocalTime kyukeiKaishiJikoku) {
        this.setKyukeiKaishiJikoku(kyukeiKaishiJikoku);
    }

    public LocalTime getKyukeiKaishiJikoku() {
        return this.kyukeiKaishiJikoku;
    }

    private void setKyukeiKaishiJikoku(LocalTime kyukeiKaishiJikoku) {
        this.kyukeiKaishiJikoku = kyukeiKaishiJikoku;
    }
}