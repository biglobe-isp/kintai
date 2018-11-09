package refactor.domain.util;

import refactor.domain.dto.TimeData;

public class TimeManager {

    public TimeData sptlitHourMinutes(String hhmm){
        TimeData data = new TimeData();
        data.setHour(Integer.valueOf(hhmm.substring(0, 2)));
        data.setMinutes(Integer.valueOf(hhmm.substring(2, 4)));
        return data;
    }

    public int calcWorkMinutes(TimeData startTime,TimeData endTime) {

        int workMinutes = endTime.getHour()  * 60 + endTime.getMinutes()
                - (startTime.getHour() * 60 + startTime.getMinutes());

        //１２時から１３時の休憩時間加味
        if(endTime.getHour() == 12) {
            workMinutes -= endTime.getMinutes();
        } else if(endTime.getHour() >= 13) {
            workMinutes -= 60;
        }
        //１８時から１９時の休憩時間加味
        if(endTime.getHour() == 18) {
            workMinutes -= endTime.getMinutes();
        } else if(endTime.getHour() >= 19) {
            workMinutes -= 60;
        }
        //２１時から２２時の休憩時間加味
        if(endTime.getHour() == 21) {
            workMinutes -= endTime.getMinutes();
        } else if(endTime.getHour() >= 22) {
            workMinutes -= 60;
        }
        return workMinutes;
    }

    public int calcOverWorkMinutes(int workMinutes){
        return Math.max(workMinutes - 8 * 60, 0);
    }
}