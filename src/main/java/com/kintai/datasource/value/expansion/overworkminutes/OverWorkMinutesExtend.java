package com.kintai.datasource.value.expansion.overworkminutes;

import com.kintai.datasource.value.OverWorkMinutes;
import com.kintai.datasource.value.WorkDate;
import com.kintai.datasource.value.WorkMinutes;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 休憩時間が変更となったことで残業時間{@link OverWorkMinutes}を拡張したValue Object
 * 2024/3/15から15:00-16:00が休憩時間となったことで、１日の労働時間が変更になったため、残業時間もそれに合わせて変更処理を加えます。
 */
public class OverWorkMinutesExtend extends OverWorkMinutes {
    /**
     * コンストラクタ
     * まず、1日の労働時間が新休憩時間によって変更となったため、親クラスに設定されている1日の労働基準時間{@link OverWorkMinutes#baseWorkMinutes}を変更します。
     * 勤務日{@link WorkDate}を確認し、2024/3/15以降であれば、新休憩時間(15:00-16:00)を考慮した残業時間を再算出します。
     * @param workMinutes 残業時間を計算するために使用する労働時間
     * @param workDate 新残業時間を算出を確認するために使用する勤務日
     * @throws ParseException 詳細は各メソッド参照
     */
    public OverWorkMinutesExtend(WorkMinutes workMinutes, WorkDate workDate) throws ParseException {
        changeBaseWorkMinutes();
        if(isCompareDate(workDate)) {
            this.overWorkMinutes = calculateOverWorkMinutes(workMinutes.getWorkMinutes());
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
     * 1日の労働基準時間を変更します。
     */
    protected void changeBaseWorkMinutes() {
        this.baseWorkMinutes = 420;
    }
}
