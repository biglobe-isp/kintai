package kintai.domain.company;

public enum BreakStartTime {

    LunchBreak(12),
    EveningBreak(18),
    NightBreak(21);

    private int value;

    BreakStartTime(int value) {
        this.value = value;
    }

    public int getBreakTime() {
        return value;
    }
}
