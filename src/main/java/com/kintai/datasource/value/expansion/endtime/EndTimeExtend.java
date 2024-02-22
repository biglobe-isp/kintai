package com.kintai.datasource.value.expansion.endtime;

import com.kintai.datasource.value.EndTime;
import com.kintai.exception.ValidatorException;

/**
 * 入力値が変更となった終業時刻{@link EndTime}を拡張したValue Object。
 * 入力変更後の終業時刻を管理するクラス。
 */
public class EndTimeExtend extends EndTime {
    /**
     * コンストラクタ
     * 実行するバリデーションチェックは親クラス({@link EndTime})と同様のため、親クラスを呼び出してバリデーションチェックを実施します。
     * @param endTime 終業時刻
     * @throws ValidatorException パラメータチェックで異常を検知した場合、{@link ValidatorException}をスローします。
     */
    public EndTimeExtend(String endTime) throws ValidatorException {
        super(endTime);
        this.endTime = parseEndTime(endTime);
    }

    /**
     * 終業時刻をパースします。
     * 入力値は変更になっても登録は{@link EndTime}と同様のため、本メソッドは不要となる「-end:」と「_」をパースします。
     * @param endTime 終業時刻
     * @return パース後の終業時刻
     */
    protected String parseEndTime(String endTime) {
        return endTime.replace("-end:", "").replace("_", "");
    }

    /**
     * 時刻の形式チェックをします。
     * 親クラスの形式チェック{@link EndTime#isTime(String)}をオーバーライド。
     * チェックしたい形式は親クラスと同様のため、入力値をパースした値を親クラスの形式チェックに連携します。
     * @param endTime 終業時刻
     * @throws ValidatorException 形式チェックで異常を検知した場合、{@link ValidatorException}をスローします。
     */
    @Override
    protected void isTime(String endTime) throws ValidatorException {
        String parsedEndTime = parseEndTime(endTime);
        super.isTime(parsedEndTime);
    }
}
