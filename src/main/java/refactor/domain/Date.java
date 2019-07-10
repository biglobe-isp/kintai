package refactor.domain;

public class Date {
    private final int year;
    private final int month;
    private final int day;

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public String inYYYMMDD() {
        return String.format("%d%02d%02d", year, month, day);
    }
}
