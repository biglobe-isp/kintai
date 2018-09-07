package kintai.domain.company;

public enum BreakEndTime {

    LunchBreak(13),
    EveningBreak(19),
    NightBreak(22);

    private int value;

    BreakEndTime(int value) {
        this.value = value;
    }

    public int getBreakTime() {
        return value;
    }
}
