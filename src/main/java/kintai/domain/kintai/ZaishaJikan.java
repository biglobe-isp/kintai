package kintai.domain.kintai;

import java.time.Duration;

public class ZaishaJikan {
    private Duration zaishaJikan;

    public ZaishaJikan(Duration zaishaJikan) {
        this.setZaishaJikan(zaishaJikan);
    }

    public ZaishaJikan(ShukkinJikoku shukkinJikoku, TaikinJikoku taikinJikoku) {
        this.setZaishaJikan(Duration.between(shukkinJikoku.getShukkinJikoku(), taikinJikoku.getTaikinJikoku()));
    }

    public Duration getZaishaJikan() {
        return this.zaishaJikan;
    }

    private void setZaishaJikan(Duration zaishaJikan) {
        this.zaishaJikan = zaishaJikan;
    }

    public ZaishaJikan minus(KyukeiJikan kyukeiJikan) {
        return new ZaishaJikan(this.zaishaJikan.minus(kyukeiJikan.getKyukeiJikan()));
    }
    
    public KinmuJikan asKinmuJikan() {
        return new KinmuJikan(this.zaishaJikan);
    }
}