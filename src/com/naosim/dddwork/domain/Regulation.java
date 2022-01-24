package src.com.naosim.dddwork.domain;

public class Regulation {
    private String dateTime;
    private int startMinute = 0;
    private int startHour = 0;
    private int endMinute = 0;
    private int endHour = 0;
    private int workMinutes = 0;
    private int overWorkMinutes = 0;

    public Regulation(String dateT, int startH, int startM, int endH, int endM) {
        this.dateTime = dateT;
        this.startHour = startH;
        this.startMinute = startM;
        this.endHour = endH;
        this.endMinute = endM;
    }

    public void workTime() {
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

    public void overTime() {
        this.overWorkMinutes = Math.max(this.workMinutes - 8 * 60, 0);
    }

    public String getDate() {
        return this.dateTime;
    }

    public String getStart() {
        return String.format("%02d", this.startHour) + "" +  String.format("%02d", this.startMinute) ;
    }

    public String getEnd() {
        return String.format("%02d", this.endHour) + "" + String.format("%02d", this.endMinute);
    }

    public int getWorkMinutes() {
        return this.workMinutes;
    }

    public int getOverWorkMinutes() {
        return this.overWorkMinutes;
    }

}
