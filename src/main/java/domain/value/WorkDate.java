package domain.value;

import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode
public final class WorkDate {
    private final LocalDate workDate;

    public WorkDate(int year, int month, int day) {
        if (year < 1900 || 9999 < year) {
            throw new IllegalArgumentException("年は1900年から9999年の間である必要があります。");
        }

        this.workDate = LocalDate.of(year, month, day);
    }

    @Override
    public String toString() {
        return String.format("%04d%02d%02d", workDate.getYear(), workDate.getMonthValue(), workDate.getDayOfMonth());
    }

    public boolean belongs(WorkYearMonth workYearMonth) {
        return this.workDate.getYear() == workYearMonth.getYear() && this.workDate.getMonthValue() == workYearMonth.getMonth();
    }

    boolean isBefore(WorkDate workDate) {
        return this.workDate.isBefore(workDate.workDate);
    }
 }
