package kintai.domain;

import lombok.Value;

import java.time.Duration;
import java.time.LocalDateTime;

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
}
