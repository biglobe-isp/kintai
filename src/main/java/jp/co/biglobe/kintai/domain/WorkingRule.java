package jp.co.biglobe.kintai.domain;

public class WorkingRule {

    private static final int coolTimes[][] ={{12,13}, {18,19},{21,22}};
    private static final int coolTimeIndex_StartTime = 0;
    private static final int coolTimeIndex_EntTime = 1;

    //休憩時間クラスで表現を変更する

    private static WorkingRule INSTANCE;

    private WorkingRule(){
    }

    public static  WorkingRule getInstance(){
        if(INSTANCE == null){
            INSTANCE = new WorkingRule();
        }
        return INSTANCE;
    }

    public WorkTime calculateWorkTime(WorkTime workTime){
        WorkTimeBuilder builder = new WorkTimeBuilder();

        int startH = workTime.getStartTime().getHoursAsInt();
        int startM = workTime.getStartTime().getMinutesAsInt();

        int endH = workTime.getEndTime().getHoursAsInt();
        int endM = workTime.getEndTime().getMinutesAsInt();
        int workMinutes = endH * 60 + endM - (startH * 60 + startM);

        for (int i = 0; i < coolTimes.length; i++) {
            if(endH == coolTimes[i][coolTimeIndex_StartTime]){
                workMinutes -= endM;
            }else  if (endH >= coolTimes[i][coolTimeIndex_EntTime]) {
                workMinutes -= 60;
            }
        }

        return builder.buildWorkTime(workTime,workMinutes,Math.max(workMinutes - 8 * 60, 0));
    }

    public WorkTime calculateWorkTime(WorkDate workDate, StartWorkTime startWorkTime, EndWorkTime endWorkTime, NowTime nowTime){

        int startH = Integer.valueOf(startWorkTime.getTime().substring(0, 2));
        int startM = Integer.valueOf(startWorkTime.getTime().substring(2, 4));

        int endH = Integer.valueOf(endWorkTime.getTime().substring(0, 2));
        int endM = Integer.valueOf(endWorkTime.getTime().substring(2, 4));

        int workMinutes = endH * 60 + endM - (startH * 60 + startM);

        for (int i = 0; i < coolTimes.length; i++) {
            if(endH == coolTimes[i][coolTimeIndex_StartTime]){
                workMinutes -= endM;
            }else  if (endH >= coolTimes[i][coolTimeIndex_EntTime]) {
                workMinutes -= 60;
            }
        }

        WorkTime workTime = new WorkTime(
                workDate,
                startWorkTime,
                endWorkTime,
                workMinutes,
                Math.max(workMinutes - 8 * 60, 0),
                nowTime
        );

        return workTime;
    }
}
