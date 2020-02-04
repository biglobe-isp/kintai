package kintai.domain.kintai;

public class ShukeiKekka {
    KinmuJikan shukeiKinmuJikan;
    ZangyoJikan shukeiZangyoJikan;

    public ShukeiKekka(KinmuJikan shukeiKinmuJikan, ZangyoJikan shukeiZangyoJikan) {
        this.setShukeiKinmuJikan(shukeiKinmuJikan);
        this.setShukeiZangyoJikan(shukeiZangyoJikan);
    }

    public KinmuJikan getShukeiKinmuJikan() {
        return this.shukeiKinmuJikan;
    }

    public void setShukeiKinmuJikan(KinmuJikan shukeiKinmuJikan) {
        this.shukeiKinmuJikan = shukeiKinmuJikan;
    }

    public ZangyoJikan getShukeiZangyoJikan() {
        return this.shukeiZangyoJikan;
    }

    public void setShukeiZangyoJikan(ZangyoJikan shukeiZangyoJikan) {
        this.shukeiZangyoJikan = shukeiZangyoJikan;
    }
}