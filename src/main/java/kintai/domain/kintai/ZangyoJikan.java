package kintai.domain.kintai;

import java.time.Duration;

public class ZangyoJikan {
    private Duration zangyoJikan;

    public ZangyoJikan(Duration zangyoJikan) {
        this.setZangyoJikan(zangyoJikan);
    }

    public ZangyoJikan(KinmuJikan kinmuJikan, KinmuJikan tsujoKinmuJikan) {
        if (!kinmuJikan.isLongerThan(tsujoKinmuJikan)) {
            this.setZangyoJikan(Duration.ZERO);
            return;
        }

        this.setZangyoJikan(kinmuJikan.minus(tsujoKinmuJikan).getKinmuJikan());
    }

    public static ZangyoJikan zeroZangyoJikan() {
        return new ZangyoJikan(Duration.ZERO);
    }

    public Duration getZangyoJikan() {
        return this.zangyoJikan;
    }

    private void setZangyoJikan(Duration zangyoJikan) {
        this.zangyoJikan = zangyoJikan;
    }

    public ZangyoJikan plus(ZangyoJikan other) {
        return new ZangyoJikan(this.zangyoJikan.plus(other.zangyoJikan));
    }
}