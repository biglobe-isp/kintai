package refactor.domain;

public class YearMonth {
    private final int year;
    private final int month;

    public YearMonth(String yyyymm) {
        this.year = Integer.valueOf(yyyymm.substring(0, 4));
        this.month = Integer.valueOf(yyyymm.substring(4, 6));
    }

    @Override
    public String toString() {
        return String.format("%d%02d", year, month);
    }
}
