package jp.co.biglobe.isp.kintai.domain.time_range;

import java.time.Duration;
import java.time.LocalTime;

public interface TimeRange {
    LocalTime getStartTime();
    LocalTime getEndTime();
    default TimeSpanMinutes getBreakTimeSpanMinutes(TimeRange target) {
        if(this.getEndTime().getHour() == target.getStartTime().getHour()) {
            return new TimeSpanMinutes(Duration.between(target.getStartTime(), this.getEndTime()).toMinutes());
        } else if(this.getEndTime().getHour() >= target.getEndTime().getHour()) {
            return new TimeSpanMinutes(Duration.between(target.getStartTime(), target.getEndTime()).toMinutes());
        } else return new TimeSpanMinutes(0);
    }
//    default TimeRange getBreakTimeSpanMinutes(TimeRange target) {
//        if(this.getEndTime().getHour() == target.getStartTime().getHour()) {
//            return List.of(new WorkTime(this.getStartTime(), target.getStartTime()), new WorkTime(target.getEndTime(), this.getEndTime()));
//        } else if(this.getEndTime().getHour() >= target.getEndTime().getHour()) {
//            return List.of(new WorkTime(this.getStartTime(), target.getStartTime()));
//        } else return List.empty();
//    }

    default TimeSpanMinutes getTimeSpanMinutes() {
        return new TimeSpanMinutes(Duration.between(this.getStartTime(), this.getEndTime()).toMinutes());
    }

}
