package com.kintai.datasource.value.expansion.workminutes;

import com.kintai.datasource.value.WorkDate;
import com.kintai.datasource.value.WorkMinutes;
import com.kintai.datasource.value.WorkTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 休憩時間が変更となったことで労働時間{@link WorkMinutes}を拡張したValue Object
 * 2024/3/15から15:00-16:00が休憩時間となったことで、１日の労働時間が変更になったため、その処理を実施します。
 */
public class WorkMinutesExtend extends WorkMinutes {
    /**
     * コンストラクタ
     * 親クラス{@link WorkMinutes}に労働時刻{@link WorkTime}を連携し、通常通り労働時間を算出します。
     * 算出後は勤務日{@link WorkDate}を確認し、2024/3/15以降であれば、新休憩時間(15:00-16:00)を考慮した労働時間を再算出します。
     * @param workTime 労働時間を計算するために使用する労働時刻
     * @param workDate 新休憩時間を算出を確認するために使用する勤務日
     * @throws ParseException 詳細は各メソッド参照
     */
    public WorkMinutesExtend(WorkTime workTime, WorkDate workDate) throws ParseException {
        super(workTime);
        if(isCompareDate(workDate)) {
            // 親クラスで算出した労働時間から新休憩時間分を差し引く。
            this.workMinutes -= calculateAddRest(workTime.getEndTime().getEndTime());
        }
    }

    /**
     * 勤務日が2024/3/15以降であるか比較します。
     * @param workDate 勤務日
     * @return 勤務日が2024/3/15以降であればtrue。2024/3/14前であればfalse
     * @throws ParseException 勤務日の日時形式(yyyyMMdd)出ない場合、{@link ParseException}をスローします。
     */
    protected boolean isCompareDate(WorkDate workDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        // 勤務日が2024/3/15以降であれば、0か1が返却される。2024/3/14以前であれば-1になる。
        return dateFormat.parse(workDate.getWorkDate()).compareTo(dateFormat.parse("20240315")) != -1;
    }

    /**
     * 新休憩時間(15:00-16:00)を算出します。
     * 終業時刻が15時より前であれば、0分を返却。
     * 15:00-15:59の間であれば、その分数分(例えば、終業時刻が15:30であれば30分は休憩時間として算出)を返却。
     * 終業時刻が16:00以降であれば60分を返却。
     * @param endTime 終業時刻
     * @return 休憩時間
     */
    protected int calculateAddRest(String endTime) {
        int endH = parseInt(endTime.substring(0, 2));
        int endM = parseInt(endTime.substring(2, 4));
        int minusWorkMinutes = 0;
        if (endH == 15) {
            minusWorkMinutes = endM;
        } else if (endH >= 16) {
            minusWorkMinutes = 60;
        }
        return minusWorkMinutes;
    }

}
