package refactor.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@EqualsAndHashCode
public class WorkingDay {
    private static final DateTimeFormatter WORKING_DAY_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");
    @NonNull
    private final LocalDate localDate;

    public static WorkingDay fromString(@NonNull String yyymmdd) {
        LocalDate localDate = LocalDate.parse(yyymmdd, WORKING_DAY_FORMAT);
        return new WorkingDay(localDate);
    }

    @Override
    public String toString() {
        return localDate.format(WORKING_DAY_FORMAT);
    }
}
