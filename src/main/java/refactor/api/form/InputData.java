package refactor.api.form;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

/**
 * 入力用データForm
 */
public class InputData {

    /**
     * 日付け
     */
    private String date;

    /**
     * 始業時間
     */
    private String startTime;

    /**
     * 終業時間
     */
    private String endTime;

    /**
     * 現在時刻
     */
    private String nowTime;

    public InputData() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getNowTime() {
        return nowTime;
    }

    public void setNowTime(String nowTime) {
        this.nowTime = nowTime;
    }
}
