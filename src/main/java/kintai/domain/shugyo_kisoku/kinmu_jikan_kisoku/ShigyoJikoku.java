package kintai.domain.shugyo_kisoku.kinmu_jikan_kisoku;

import java.time.LocalTime;

import kintai.domain.kintai.ShukkinJikoku;

public class ShigyoJikoku {
    private LocalTime shigyoJikoku;

    public ShigyoJikoku(LocalTime shigyoJikoku) {
        this.setShigyoJikoku(shigyoJikoku);
    }

    public LocalTime getShigyoJikoku() {
        return this.shigyoJikoku;
    }

    private void setShigyoJikoku(LocalTime shigyoJikoku) {
        this.shigyoJikoku = shigyoJikoku;
    }

    public ShukkinJikoku toShukkinJikoku() {
        return new ShukkinJikoku(this.shigyoJikoku);
    }
}