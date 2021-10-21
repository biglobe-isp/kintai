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
    LocalDateTime start;

    /**
     * 退勤時間
     */
    LocalDateTime end;

    /**
     * 成形した出社時間文字列を出力する.
     * .
     * @return 出社時間文字列
     */
    public String formatStart() {
        return formatLocalDateTime(start);
    }

    /**
     * 成形した退勤時間文字列を出力する.
     *
     * @return 退勤時間文字列
     */
    public String formatEnd() {
        return formatLocalDateTime(end);
    }

    /**
     * 労働時間を算出.
     *
     * @return 労働時間
     */
    public WorkDuration calculateWorkDuration() {
        int LUNCH_BREAK_START_TIME = 12;
        int LUNCH_BREAK_END_TIME = 13;
        int NIGHT_BREAK_START_TIME = 18;
        int NIGHT_BREAK_END_TIME = 19;
        int MID_NIGHT_BREAK_START_TIME = 21;
        int MID_NIGHT_BREAK_END_TIME = 22;
        int BREAK_DURATION = 60;
        Duration duration = Duration.between(start, end);

        if (end.getHour() == LUNCH_BREAK_START_TIME) {
            duration = duration.minusMinutes(end.getMinute());
        } else if (end.getHour() >= LUNCH_BREAK_END_TIME) {
            duration = duration.minusMinutes(BREAK_DURATION);
        }

        if (end.getHour() == NIGHT_BREAK_START_TIME) {
            duration = duration.minusMinutes(end.getMinute());
        } else if (end.getHour() >= NIGHT_BREAK_END_TIME) {
            duration = duration.minusMinutes(BREAK_DURATION);
        }

        if (end.getHour() == MID_NIGHT_BREAK_START_TIME) {
            duration = duration.minusMinutes(end.getMinute());
        } else if (end.getHour() >= MID_NIGHT_BREAK_END_TIME) {
            duration = duration.minusMinutes(BREAK_DURATION);
        }
        return new WorkDuration(duration);
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
