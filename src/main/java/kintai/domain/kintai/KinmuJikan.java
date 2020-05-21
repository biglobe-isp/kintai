package kintai.domain.kintai;

import java.time.Duration;

public class KinmuJikan {
    private Duration kinmuJikan;

    public KinmuJikan(Duration kinmuJikan) {
        this.setKinmuJikan(kinmuJikan);
    }

    public static KinmuJikan zeroKinmuJikan() {
        return new KinmuJikan(Duration.ZERO);
    }

    public Duration getKinmuJikan() {
        return this.kinmuJikan;
    }

    private void setKinmuJikan(Duration kinmuJikan) {
        if (kinmuJikan.isNegative()) {
            this.kinmuJikan = Duration.ofMinutes(0);
            return;
        }
        this.kinmuJikan = kinmuJikan;
    }

    public KinmuJikan plus(KinmuJikan other) {
        return new KinmuJikan(this.kinmuJikan.plus(other.kinmuJikan));
    }

    public KinmuJikan minus(KinmuJikan other) {
        return new KinmuJikan(this.kinmuJikan.minus(other.kinmuJikan));
    }

    public boolean isLongerThan(KinmuJikan other) {
        return this.kinmuJikan.compareTo(other.kinmuJikan) == 1;   
    }
}