package jp.co.esumit.kintai.domain.kintai_record.time_card;

import lombok.Value;
import lombok.val;

@Value
public class TimeCard {
    StartTime startTime;
    EndTime endTime;

    public TimeCard(StartTime startTime, EndTime endTime) {
        valid(startTime, endTime);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getOperatingMinutes() {
        return endTime.getMinutes() - startTime.getMinutes();
    }

    private void valid(StartTime startTime, EndTime endTime) {

        val start = startTime.getStartTime();
        val end = endTime.getEndTime();

        if (start.isAfter(end)) {
            throw new RuntimeException("終了時刻は開始時刻より後に設定してください。");
        }
    }
}
