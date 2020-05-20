package kintai.domain.kintai;

import java.time.LocalTime;

public class KyukeiShuryoJikoku {
    private LocalTime kyukeiShuryoJikoku;

    public KyukeiShuryoJikoku(LocalTime kyukeiShuryoJikoku) {
        this.setKyukeiShuryoJikoku(kyukeiShuryoJikoku);
    }

    public KyukeiShuryoJikoku(TaikinJikoku taikinJikoku) {
        this.setKyukeiShuryoJikoku(taikinJikoku.getTaikinJikoku());
    }

    public LocalTime getKyukeiShuryoJikoku() {
        return this.kyukeiShuryoJikoku;
    }

    private void setKyukeiShuryoJikoku(LocalTime kyukeiShuryoJikoku) {
        this.kyukeiShuryoJikoku = kyukeiShuryoJikoku;
    }
}