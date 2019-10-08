package domain;

public class KintaiCalcurator {
    private int startMinute;
    private int startHour;
    private int endMinute;
    private int endHour;
    private String date;

    private int workMinutes = 0;
    private int overWorkMinutes = 0;


    //TODO 開始時間と終了時間はHHMMで保持
    //TODO 実労働、残業時間は必要になった時に計算して返却。（calucurateほげは不要）
    //TODO 休憩時間のロジックは何とかしたい（計算方法とかとか）


    public KintaiCalcurator(String date, int startH, int startM, int endH, int endM) {
        this.date = date;
        this.startHour = startH;
        this.startMinute = startM;
        this.endHour = endH;
        this.endMinute = endM;
    }

    public void calcurateJiturodo() {
        int workMinutes = this.endHour * 60 + this.endMinute - (this.startHour * 60 + this.startMinute);

        if (this.endHour == 12) {
            workMinutes -= this.endMinute;
        } else if (endHour >= 13) {
            workMinutes -= 60;
        }

        if (endHour == 18) {
            workMinutes -= this.endMinute;
        } else if (endHour >= 19) {
            workMinutes -= 60;
        }

        if (endHour == 21) {
            workMinutes -= this.endMinute;
        } else if (endHour >= 22) {
            workMinutes -= 60;
        }

        this.workMinutes = workMinutes;

    }

    public void calcurateZangyo() {
        this.overWorkMinutes = Math.max(this.workMinutes - 8 * 60, 0);

    }

    public int getWorkMinutes() {
        return this.workMinutes;
    }

    public int getOverWorkMinutes() {
        return this.overWorkMinutes;
    }

    public String getStart() {
        return this.startHour + "" + this.startMinute;
    }

    public String getEnd() {
        return this.endHour + "" + this.endMinute;
    }
}
