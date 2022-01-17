package domain.value;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public final class WorkYearMonth {
    private final int year;
    private final int month;

    public WorkYearMonth(int year, int month) {
        if (year < 1900 || 9999 < year) {
            throw new IllegalArgumentException("年は1900年から9999年の間である必要があります。");
        }

        if (month < 1 || 12 < month) {
            throw new IllegalArgumentException("月は1月から12月の間である必要があります。");
        }

        this.year = year;
        this.month = month;
    }
}
