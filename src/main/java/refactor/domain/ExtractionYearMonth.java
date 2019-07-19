package refactor.domain;

import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
public class ExtractionYearMonth {
    @NonNull
    private final YearMonth yearMonth;

    @Override
    public String toString() {
        return yearMonth.format(DateTimeFormatter.ofPattern("yyyyMM"));
    }
}
