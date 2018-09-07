package kintai.domain;

public class FixedMinutes {

    private int value;

    public FixedMinutes(int value) {
        this.value = value;
    }

    static FixedMinutes create(SyukkinTime syukkinTime, TaikinTime taikinTime) {

        int fixedMinutes = 8 * 60;
        return new FixedMinutes(fixedMinutes);
    }

    public int getValue() {
        return value;
    }
}
