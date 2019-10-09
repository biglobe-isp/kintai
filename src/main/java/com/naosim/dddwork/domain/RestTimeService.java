package com.naosim.dddwork.domain;

import java.time.Duration;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

/***
 * 指定された休憩時間に基づき、休憩した時間を計算する
 */
public class RestTimeService {
    List<Rest> list = new LinkedList<>();


    public RestTimeService() {
        //デフォルトの休憩時間
        this.list.add(new Rest(12, 00, 13, 00));
        this.list.add(new Rest(18, 00, 19, 00));
        this.list.add(new Rest(21, 00, 22, 00));
    }


    public RestTimeService(Rest[] list) {
        for (Rest r : list) {
            this.list.add(r);
        }
    }

    /***
     * 指定された開始時刻、終了時刻のから休憩時間扱いになった時間（分）導出する。
     * @param start
     * @param end
     * @return
     */
    public long calculateRestTime(WorkTime start, WorkTime end) {
        long result = 0;
        LocalTime workStart = start.getLocalTIme();
        LocalTime workEnd = end.getLocalTIme();
        for (Rest r : list) {
            LocalTime restStart = r.getStartLocalTime();
            LocalTime restEnd = r.getEndLocalTime();


            //労働時間が休憩時間に重なっていないならその休憩時間は0分
            if (restEnd.isBefore(workStart) || restStart.isAfter(workEnd)) {
                result += 0;
                continue;
            }
            //重なっている場合
            //重なっている時間を元に休憩時間を計算する。
            LocalTime s = restStart.isAfter(workStart) ? restStart : workStart;
            LocalTime e = restEnd.isBefore(workEnd) ? restEnd : workEnd;
            result += Duration.between(s, e).toMinutes();

        }
        return result;

    }
}
