package domain;

import japan.EndTime;

public class RestTimeCalculator {

    public int calcTotalRestTime(EndTime endTime) {

        int endHour = endTime.getEndTimeHour();
        int endMinute = endTime.getEndTimeMinute();
        
        //合計休憩時間
        int totalRestMinute = 0;

        //12時台に退勤したら、分数は労働時間からカットする。
        //13時以降まで働いたら、休憩時間の60分間は労働時間からカットする。
        if (endHour == 12) {
            totalRestMinute += endMinute;
        } else if (endHour >= 13) {
            totalRestMinute += 60;
        }

        //18時台に退勤したら、分数は労働時間からカットする。
        //19時以降まで働いたら、休憩時間の60分間は労働時間からカットする。
        if (endHour == 18) {
            totalRestMinute += endMinute;
        } else if (endHour >= 19) {
            totalRestMinute += 60;
        }

        //21時台に退勤したら、分数は労働時間からカットする。
        //22時以降まで働いたら、休憩時間の60分間は労働時間からカットする。
        if (endHour == 21) {
            totalRestMinute += endMinute;
        } else if (endHour >= 22) {
            totalRestMinute += 60;
        }
        
        return totalRestMinute;
    }
}
