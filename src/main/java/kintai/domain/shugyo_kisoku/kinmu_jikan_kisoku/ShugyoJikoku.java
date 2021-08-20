package kintai.domain.shugyo_kisoku.kinmu_jikan_kisoku;

import java.time.LocalTime;

import kintai.domain.kintai.TaikinJikoku;

public class ShugyoJikoku {
    private LocalTime shugyoJikoku;

    public ShugyoJikoku(LocalTime shugyoJikoku) {
        this.setShugyoJikoku(shugyoJikoku);
    }

    public LocalTime getShugyoJikoku() {
        return this.shugyoJikoku;
    }

    private void setShugyoJikoku(LocalTime shugyoJikoku) {
        this.shugyoJikoku = shugyoJikoku;
    }

    public TaikinJikoku toTaikinJikoku() {
        return new TaikinJikoku(this.shugyoJikoku);
    }
}