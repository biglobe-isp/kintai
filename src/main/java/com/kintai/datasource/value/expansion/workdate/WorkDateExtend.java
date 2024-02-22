package com.kintai.datasource.value.expansion.workdate;

import com.kintai.datasource.value.WorkDate;
import com.kintai.exception.ValidatorException;

/**
 * 入力値が変更となった勤務日{@link WorkDate}を拡張したValue Object
 * 入力変更後の勤務日を管理するクラス
 */
public class WorkDateExtend extends WorkDate {
    /**
     * コンストラクタ
     * 実行するバリデーションチェックは親クラス({@link WorkDate})と同様のため、親クラスを呼び出してバリデーションチェックを実施します。
     * @param workDate 勤務日
     */
    public WorkDateExtend(String workDate) throws ValidatorException {
        super(workDate);
        this.workDate = parseWorkDate(workDate);
    }

    /**
     * 勤務日をパースします。
     * 入力値は変更になっても登録は{@link WorkDate}と同様のため、本メソッドは不要となる「-date:」をパースします。
     * @param workDate 勤務日
     * @return パース後の勤務日
     */
    protected String parseWorkDate(String workDate) {
        return workDate.replace("-date:", "");
    }

    /**
     * 日時の形式チェックをします。
     * 親クラスの形式チェック{@link WorkDate#isDate(String)}をオーバーライド。
     * チェックしたい形式は親クラスと同様のため、入力値をパースした値を親クラスの形式チェックに連携します。
     * @param workDate 勤務日
     * @throws ValidatorException 形式チェックで異常を検知した場合、{@link ValidatorException}をスローします。
     */
    @Override
    protected void isDate(String workDate) throws ValidatorException {
        String parsedWorkDate = parseWorkDate(workDate);
        super.isDate(parsedWorkDate);
    }

}
