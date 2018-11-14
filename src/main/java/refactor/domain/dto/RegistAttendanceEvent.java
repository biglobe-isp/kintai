package refactor.domain.dto;

import refactor.domain.dto.Item.ArgsItem;
import refactor.domain.dto.Item.DateItem;
import refactor.domain.dto.Item.EndTimeItem;
import refactor.domain.dto.Item.StartTimeItem;
import refactor.domain.repository.NowTimeRepository;


/**
 * 入力用データForm
 */
public class RegistAttendanceEvent {

    private final String methodType;
    /**
     * 日付け
     */
    private final DateItem date;

    /**
     * 始業時間
     */
    private final StartTimeItem startTime;

    /**
     * 終業時間
     */
    private final EndTimeItem endTime;

    /**
     * 現在時刻
     */
    private final String nowTime;

//NOWTIMEで値を渡しで直接渡してOK
    public RegistAttendanceEvent(ArgsItem argsInput, NowTimeRepository nowTimeRepo) {
        String[] args = argsInput.getArgs();
        methodType = args[0];
        date = new DateItem(args[1]);
        startTime = new StartTimeItem(args[2]);
        endTime = new EndTimeItem(args[3]);
        nowTime = nowTimeRepo.getNowTime();
    }

    public String getMethodType() {
        return methodType;
    }

    public DateItem getDate() {
        return date;
    }

    public StartTimeItem getStartTime() {
        return startTime;
    }

    public EndTimeItem getEndTime() {
        return endTime;
    }

    public String getNowTime() {
        return nowTime;
    }

    /**
     * 勤務時間を計算する
     * @return 勤務時間
     */
    public int calculateWorkTime(){

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

    /**
     * 残業時間を計算する
     * @return 残業時間
     */
    public int caluculateOverWorkTime(){
        return Math.max(calculateWorkTime() - 8 * 60, 0);
    }
}
