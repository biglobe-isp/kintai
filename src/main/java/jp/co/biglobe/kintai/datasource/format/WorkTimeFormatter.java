package jp.co.biglobe.kintai.datasource.format;

import jp.co.biglobe.kintai.domain.WorkTime;

public class WorkTimeFormatter {

    public static String format(WorkTime workTime){
        return String.format("%s,%s,%s,%s,%s,%s\n", workTime.getDate().getWorkDate(),
                workTime.getStartTime().getTime(), workTime.getEndTime().getTime(),
                workTime.getMinutes(), workTime.getOverWorkMinutes(), workTime.getNow().getNow());
    }
}
