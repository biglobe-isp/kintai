package datasource;

/**
 * 労働時間を計算するクラス。
 */
public class WorkTimeCalculator {

    public int calcDayWorkMinute(int startHour, int startMinute, int endHour, int endMinute){

        int dayWorkMinute = endHour * 60 + endMinute - (startHour * 60 + startMinute);

        //12時台に退勤したら、分数は労働時間からカットする。
        //13時以降まで働いたら、休憩時間の60分間は労働時間からカットする。
        if (endHour == 12) {
            dayWorkMinute -= endMinute;
        } else if (endHour >= 13) {
            dayWorkMinute -= 60;
        }

        //18時台に退勤したら、分数は労働時間からカットする。
        //19時以降まで働いたら、休憩時間の60分間は労働時間からカットする。
        if (endHour == 18) {
            dayWorkMinute -= endMinute;
        } else if (endHour >= 19) {
            dayWorkMinute -= 60;
        }

        //21時台に退勤したら、分数は労働時間からカットする。
        //22時以降まで働いたら、休憩時間の60分間は労働時間からカットする。
        if (endHour == 21) {
            dayWorkMinute -= endMinute;
        } else if (endHour >= 22) {
            dayWorkMinute -= 60;
        }

        return dayWorkMinute;

    }









}
