package kintai.domain.kintai;

import kintai.domain.util.Clock;

public class Kintai {
    private Kinmubi kinmubi;
    private ShukkinJikoku shukkinJikoku;
    private TaikinJikoku taikinJikoku;
    private KinmuJikan kinmuJikan;
    private ZangyoJikan zangyoJikan;
    private NyuryokuJikoku nyuryokuJikoku;

    public Kintai(
        Kinmubi kinmubi,
        ShukkinJikoku shukkinJikoku,
        TaikinJikoku taikinJikoku,
        KinmuJikan kinmuJikan,
        ZangyoJikan zangyoJikan,
        NyuryokuJikoku nyuryokuJikoku
    ) {
        this.setKinmubi(kinmubi);
        this.setShukkinJikoku(shukkinJikoku);
        this.setTaikinJikoku(taikinJikoku);
        this.setKinmuJikan(kinmuJikan);
        this.setZangyoJikan(zangyoJikan);
        this.setNyuryokuJikoku(nyuryokuJikoku);
    }

    public static Kintai nyuryoku(
        Kinmubi kinmubi,
        ShukkinJikoku shukkinJikoku,
        TaikinJikoku taikinJikoku,
        KinmuJikan kinmuJikan,
        ZangyoJikan zangyoJikan,
        Clock clock
    ) {
        return new Kintai(
            kinmubi,
            shukkinJikoku,
            taikinJikoku,
            kinmuJikan,
            zangyoJikan,
            NyuryokuJikoku.now(clock)
        );
    }

    public Kinmubi getKinmubi() {
        return this.kinmubi;
    }

    private void setKinmubi(Kinmubi kinmubi) {
        this.kinmubi = kinmubi;
    }

    public ShukkinJikoku getShukkinJikoku() {
        return this.shukkinJikoku;
    }

    private void setShukkinJikoku(ShukkinJikoku shukkinJikoku) {
        this.shukkinJikoku = shukkinJikoku;
    }

    public TaikinJikoku getTaikinJikoku() {
        return this.taikinJikoku;
    }

    private void setTaikinJikoku(TaikinJikoku taikinJikoku) {
        this.taikinJikoku = taikinJikoku;
    }

    public KinmuJikan getKinmuJikan() {
        return this.kinmuJikan;
    }

    private void setKinmuJikan(KinmuJikan kinmuJikan) {
        this.kinmuJikan = kinmuJikan;
    }

    public ZangyoJikan getZangyoJikan() {
        return this.zangyoJikan;
    }

    private void setZangyoJikan(ZangyoJikan zangyoJikan) {
        this.zangyoJikan = zangyoJikan;
    }

    public NyuryokuJikoku getNyuryokuJikoku() {
        return this.nyuryokuJikoku;
    }

    private void setNyuryokuJikoku(NyuryokuJikoku nyuryokuJikoku) {
        this.nyuryokuJikoku = nyuryokuJikoku;
    }
}