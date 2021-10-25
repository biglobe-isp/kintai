package kintai.domain;

import lombok.Value;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 勤怠時間.
 */
@Value
public class AttendanceTime {
    /**
     * 出社時間
     */
    LocalDateTime from;

    /**
     * 退勤時間
     */
    LocalDateTime to;

    /**
     * 労働時間を算出.
     *
     * @return 労働時間
     */
    public WorkDuration calculateWorkDuration() {
        Duration workRange = Duration.between(from, to);
        EmployeeRule employeeRule = new EmployeeRule();
        for (BreakRange breakRange : employeeRule.getBreakRangeList()) {
            if (from.toLocalTime().isBefore(breakRange.getFrom())
                    && to.toLocalTime().isAfter(breakRange.getTo())) {
                workRange = workRange.minus(Duration.between(breakRange.getFrom(),breakRange.getTo()));
            } else if (to.toLocalTime().isAfter(breakRange.getFrom())
                    && to.toLocalTime().isBefore(breakRange.getTo())) {
                workRange = workRange.minus(Duration.between(breakRange.getFrom(), to.toLocalTime()));
            } else if (from.toLocalTime().isBefore(breakRange.getTo())
                    && from.toLocalTime().isAfter(breakRange.getFrom())) {
                workRange = workRange.minus(Duration.between(from.toLocalTime(), breakRange.getTo()));
            }
        }
        return new WorkDuration(workRange);
     }

    /**
     * 成形した出社時間文字列を出力する.
     * .
     * @return 出社時間文字列
     */
    public String formatFrom() {
        return formatLocalDateTime(from);
    }

    /**
     * 成形した退勤時間文字列を出力する.
     *
     * @return 退勤時間文字列
     */
    public String formatTo() {
        return formatLocalDateTime(to);
    }

    /**
     * 整形したlocalDateTime文字列を出力する.
     *
     * @param localDateTime localDateTime
     * @return 整形したlocalDateTime文字列
     */
    private String formatLocalDateTime(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
    }
}
