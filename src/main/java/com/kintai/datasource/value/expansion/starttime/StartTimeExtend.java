package com.kintai.datasource.value.expansion.starttime;

import com.kintai.datasource.value.StartTime;
import com.kintai.exception.ValidatorException;

/**
 * 入力値が変更となった始業時刻{@link StartTime}を拡張したValue Object。
 * 入力変更後の始業時刻を管理するクラス。
 */
public class StartTimeExtend extends StartTime {
    /**
     * コンストラクタ
     * 実行するバリデーションチェックは親クラス({@link StartTime})と同様のため、親クラスを呼び出してバリデーションチェックを実施します。
     * @param startTime 始業時刻
     * @throws ValidatorException パラメータチェックで異常を検知した場合、{@link ValidatorException}をスローします。
     */
    public StartTimeExtend(String startTime) throws ValidatorException {
        super(startTime);
        this.startTime = parseStartTime(startTime);
    }

    /**
     * 始業時刻をパースします。
     * 入力値は変更になっても登録は{@link StartTime}と同様のため、本メソッドは不要となる「-start:」と「_」をパースします。
     * @param startTime 始業時刻
     * @return パース後の始業時刻
     */
    protected String parseStartTime(String startTime) {
        return startTime.replace("-start:", "").replace("_", "");
    }

    /**
     * 時刻の形式チェックをします。
     * 親クラスの形式チェック{@link StartTime#isTime(String)}をオーバーライド。
     * チェックしたい形式は親クラスと同様のため、入力値をパースした値を親クラスの形式チェックに連携します。
     * @param startTime 始業時刻
     * @throws ValidatorException 形式チェックで異常を検知した場合、{@link ValidatorException}をスローします。
     */
    @Override
    protected void isTime(String startTime) throws ValidatorException {
        String parsedStartTime = parseStartTime(startTime);
        super.isTime(parsedStartTime);
    }
}
