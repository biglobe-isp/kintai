package datasource;

/**
 * 残業時間を計算するクラス。
 */
public class OverWorkTimeCalculator {

    public int calcDayOverWorkMinute(int dayWorkMinute){
        return Math.max(dayWorkMinute - 8 * 60, 0);
    }

}
