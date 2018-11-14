package refactor.api.form;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import java.time.LocalDateTime;

/**
 * 入力用データForm
 */
public class RegistAttendanceEvent {

    private final String methodType;
    /**
     * 日付け
     */
    private final String date;

    /**
     * 始業時間
     */
    private final String startTime;

    /**
     * 終業時間
     */
    private final String endTime;

    /**
     * 現在時刻
     */
    private final String nowTime;

    public RegistAttendanceEvent(String[] args) {
        methodType = args[0];
        date = args[1];
        startTime = args[2];
        endTime = args[3];
        //TODO Datasource層に時刻取得処理は持っていく
        nowTime = LocalDateTime.now().toString();
    }

    public String getDate() {
        return date;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getNowTime() {
        return nowTime;
    }
}
