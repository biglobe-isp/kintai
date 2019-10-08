package domain;

import com.naosim.dddwork.datasouce.IKintai;

public class KintaiCalcurator implements IKintai {
    private String date;
    private String start;
    private String end;

    private int workMinutes = 0;
    private int overWorkMinutes = 0;

    //TODO 休憩時間のロジックは何とかしたい（計算方法とかとか）
    public KintaiCalcurator(String date, String start, String end) {
        this.date = date;
        this.start = start;
        this.end = end;

    }

    private int calcurateJiturodo() {
        int workMinutes = getEndHour() * 60 + getEndMinute() - (getStartHour() * 60 + getStartMinute());

        if (getEndHour() == 12) {
            workMinutes -= getEndMinute();
        } else if (getEndHour() >= 13) {
            workMinutes -= 60;
        }

        if (getEndHour() == 18) {
            workMinutes -= getEndMinute();
        } else if (getEndHour() >= 19) {
            workMinutes -= 60;
        }

        if (getEndHour() == 21) {
            workMinutes -= getEndMinute();
        } else if (getEndHour() >= 22) {
            workMinutes -= 60;
        }

        return workMinutes;

    }

    private int calcurateZangyo() {
        int workMinutes = calcurateJiturodo();
        return Math.max(workMinutes - 8 * 60, 0);

    }

    private int getStartHour() {
        return Integer.valueOf(this.start.substring(0, 2));
    }

    private int getStartMinute() {
        return Integer.valueOf(this.start.substring(2, 4));
    }

    private int getEndHour() {
        return Integer.valueOf(this.end.substring(0, 2));
    }

    private int getEndMinute() {
        return Integer.valueOf(end.substring(2, 4));
    }

    @Override
    public String getWorkMinutes() {
        return String.valueOf(calcurateJiturodo());
    }

    @Override
    public String getOverWorkMinutes() {
        return String.valueOf(calcurateZangyo());
    }

    @Override
    public String getDate() {
        return this.date;
    }

    @Override
    public String getStart() {
        return this.start;
    }

    @Override
    public String getEnd() {
        return this.end;
    }
}
