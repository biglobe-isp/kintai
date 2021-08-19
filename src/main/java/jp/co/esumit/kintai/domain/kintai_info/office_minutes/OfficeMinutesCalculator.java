package jp.co.esumit.kintai.domain.kintai_info.office_minutes;

import jp.co.esumit.kintai.domain.kintai_info.end_time.EndTime;
import jp.co.esumit.kintai.domain.kintai_info.start_time.StartTime;
import lombok.val;

import java.time.LocalTime;

public class OfficeMinutesCalculator {
    public OfficeMinutes calc(StartTime startTime, EndTime endtime) {

        this.valid(startTime, endtime);

        int startH = startTime.getStartHrValue();
        int startM = startTime.getStartMinValue();
        int endH = endtime.getEndHrValue();
        int endM = endtime.getEndMinValue();

        int officeMinutes = endH * 60 + endM - (startH * 60 + startM);

        officeMinutes = excludeBreaks(endtime, officeMinutes);

        return new OfficeMinutes(officeMinutes);
    }

    private int excludeBreaks(EndTime endTime, int officeMinutes) {

        // 休憩時間
        final int[][] breakTimes = {{12, 13}, {15, 16}, {18, 19}, {21, 22}};

        for (int[] breakTime : breakTimes) {
            if (endTime.getEndHrValue() == breakTime[0]) {
                officeMinutes -= endTime.getEndMinValue();
            } else if (endTime.getEndHrValue() >= breakTime[1]) {
                officeMinutes -= 60;
            }
        }

        return officeMinutes;
    }

    private void valid(StartTime startTime, EndTime endTime) {

        val start = LocalTime.of(startTime.getStartHrValue(), startTime.getStartMinValue(), 0);
        val end = LocalTime.of(endTime.getEndHrValue(), endTime.getEndMinValue(), 0);

        if (start.isAfter(end)) {
            throw new RuntimeException("終了時刻は開始時刻より後に設定してください。");
        }
    }
}
