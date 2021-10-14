package kintai.domain;

import lombok.Value;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * 労働時間.
 */
@Value
public class WorkDuration {
    int START_TIME = 9;
    int REGULAR_END_TIME = 18;
    int LUNCH_BREAK_START_TIME = 12;
    int LUNCH_BREAK_END_TIME = 13;
    int NIGHT_BREAK_START_TIME = 18;
    int NIGHT_BREAK_END_TIME = 19;
    int MID_NIGHT_BREAK_START_TIME = 21;
    int MID_NIGHT_BREAK_END_TIME = 22;
    int BREAK_DURATION = 60;

    Duration duration;

    /**
     * コンストラクタ.
     *
     * @param attendanceTime 勤怠時間
     */
    public WorkDuration(AttendanceTime attendanceTime) {
        this.duration = calculateDuration(attendanceTime);
    }

    /**
     * 労働時間を算出.
     *
     * @param attendanceTime 勤怠時間
     * @return 労働時間
     */
    private Duration calculateDuration(AttendanceTime attendanceTime) {
        LocalDateTime end = attendanceTime.getEnd();
        Duration duration = Duration.between(attendanceTime.getStart(), end);

        if (end.getHour() == LUNCH_BREAK_START_TIME) {
            duration = duration.minusMinutes(end.getMinute());
            return duration;
        } else if (end.getHour() >= LUNCH_BREAK_END_TIME) {
            duration = duration.minusMinutes(BREAK_DURATION);
        }

        if (end.getHour() == NIGHT_BREAK_START_TIME) {
            duration = duration.minusMinutes(end.getMinute());
            return duration;
        } else if (end.getHour() >= NIGHT_BREAK_END_TIME) {
            duration = duration.minusMinutes(BREAK_DURATION);
        }

        if (end.getHour() == MID_NIGHT_BREAK_START_TIME) {
            duration = duration.minusMinutes(end.getMinute());
            return duration;
        } else if (end.getHour() >= MID_NIGHT_BREAK_END_TIME) {
            duration = duration.minusMinutes(BREAK_DURATION);
        }

        return duration;
    }
}
