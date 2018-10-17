package domain;

public class WorkMinute {
    private final int workMinute;
    private int tmpWorkMinute;

    WorkMinute(int startHour, int startMinute, int endHour, int endMinute){
        this.tmpWorkMinute = endHour * 60 + endMinute - (startHour * 60 + startMinute);
        
        //12時台に退勤したら、分数は労働時間からカットする。
        //13時以降まで働いたら、休憩時間の60分間は労働時間からカットする。
        if (endHour == 12) {
            this.tmpWorkMinute -= endMinute;
        } else if (endHour >= 13) {
            this.tmpWorkMinute -= 60;
        }

        //18時台に退勤したら、分数は労働時間からカットする。
        //19時以降まで働いたら、休憩時間の60分間は労働時間からカットする。
        if (endHour == 18) {
            this.tmpWorkMinute -= endMinute;
        } else if (endHour >= 19) {
            this.tmpWorkMinute -= 60;
        }

        //21時台に退勤したら、分数は労働時間からカットする。
        //22時以降まで働いたら、休憩時間の60分間は労働時間からカットする。
        if (endHour == 21) {
            this.tmpWorkMinute -= endMinute;
        } else if (endHour >= 22) {
            this.tmpWorkMinute -= 60;
        }

        this.workMinute = this.tmpWorkMinute;

    }

    public int getWorkMinute() {
        return workMinute;
    }
}