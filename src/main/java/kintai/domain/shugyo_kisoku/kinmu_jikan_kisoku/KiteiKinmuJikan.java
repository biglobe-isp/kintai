package kintai.domain.shugyo_kisoku.kinmu_jikan_kisoku;

import java.time.Duration;
import java.time.LocalTime;

import kintai.domain.kintai.ShukkinJikoku;
import kintai.domain.kintai.TaikinJikoku;
import kintai.domain.kintai.ZaishaJikan;

public class KiteiKinmuJikan {
    private ShigyoJikoku shigyoJikoku;
    private ShugyoJikoku shugyoJikoku;

    public KiteiKinmuJikan(
        ShigyoJikoku shigyoJikoku,
        ShugyoJikoku shugyoJikoku
    ) {
        this.setShigyoJikoku(shigyoJikoku);
        this.setShugyoJikoku(shugyoJikoku);
    }

    public ShigyoJikoku getShigyoJikoku() {
        return this.shigyoJikoku;
    }

    public void setShigyoJikoku(ShigyoJikoku shigyoJikoku) {
        this.shigyoJikoku = shigyoJikoku;
    }

    public ShugyoJikoku getShugyoJikoku() {
        return this.shugyoJikoku;
    }

    public void setShugyoJikoku(ShugyoJikoku shugyoJikoku) {
        this.shugyoJikoku = shugyoJikoku;
    }

    public ZaishaJikan calculateTsujoZaishaJikan() {
        return new ZaishaJikan(Duration.between(this.shigyoJikoku.getShigyoJikoku(), this.shugyoJikoku.getShugyoJikoku()));
    }

    public ShukkinJikoku tsujoShukkinJikoku() {
        return this.shigyoJikoku.toShukkinJikoku();
    }

    public TaikinJikoku tsujoTaikinJikoku() {
        return this.shugyoJikoku.toTaikinJikoku();
    }
}