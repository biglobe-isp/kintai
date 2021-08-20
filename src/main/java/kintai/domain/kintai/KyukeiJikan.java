package kintai.domain.kintai;

import java.time.Duration;

public class KyukeiJikan {
    private Duration kyukeiJikan;

    public KyukeiJikan(Duration kyukeiJikan) {
        this.setKyukeiJikan(kyukeiJikan);
    }

    public KyukeiJikan(KyukeiKaishiJikoku kyukeiKaishiJikoku, KyukeiShuryoJikoku kyukeiShuryoJikoku) {
        this.setKyukeiJikan(Duration.between(kyukeiKaishiJikoku.getKyukeiKaishiJikoku(),
                kyukeiShuryoJikoku.getKyukeiShuryoJikoku()));
    }

    public Duration getKyukeiJikan() {
        return this.kyukeiJikan;
    }

    private void setKyukeiJikan(Duration kyukeiJikan) {
        this.kyukeiJikan = kyukeiJikan;
    }

    public KyukeiJikan plus(KyukeiJikan other) {
        return new KyukeiJikan(this.kyukeiJikan.plus(other.kyukeiJikan));
    }

}