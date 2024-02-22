package com.kintai.datasource.value.expansion.password;

import com.kintai.datasource.enums.DayOfWeekKintai;
import com.kintai.datasource.value.Password;
import com.kintai.exception.ValidatorException;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * CSVファイルを操作するためのパスワードを管理するパスワード{@link Password}を拡張Value Object。
 * CSVのパスワードは外部サービスで管理していないため、本クラスのバリデーションチェック内にて認証チェックも同様に実施します。
 */
public class CsvPassword extends Password {
    /**
     * コンストラクタ
     * 実行するバリデーションチェックは親クラス({@link Password})と同様のため、親クラスを呼び出してバリデーションチェックを実施します。
     * @param password パスワード
     * @throws ValidatorException パラメータチェックで異常を検知した場合、{@link ValidatorException}をスローします。
     */
    public CsvPassword(String password) throws ValidatorException {
        super(password);
    }

    /**
     * バリデーションチェックを実施します。
     * パスワード一致チェックを追加するため、親クラスの形式チェック{@link Password#validate(String)}をオーバーライド。
     *
     * @param password パスワード
     * @throws ValidatorException チェックで異常を検知した場合、{@link ValidatorException}をスローします。
     */
    @Override
    protected void validate(String password) throws ValidatorException {
        isRequired(password);
        matchPassword(password);
    }

    /**
     * パスワード一致チェックメソッド。
     * パスワードとアプリ実行時の曜日(SundayやMonday)が一致しているかをチェックします。
     * @param password パスワード
     * @throws ValidatorException パスワードが不一致の場合、{@link ValidatorException}をスローします。
     */
    protected void matchPassword(String password) throws ValidatorException {
        DayOfWeekKintai todayDayOfWeek = getTodayDayOfWeekKintai();
        if(!todayDayOfWeek.getDayOfName().equals(password)) {
            throw new ValidatorException("パスワードが一致しません。");
        }
    }

    /**
     * アプリ実行時の曜日を取得します。
     * 現在日時から曜日を取得し、{@link DayOfWeekKintai}から曜日名を持つクラスを返却します。
     * @return アプリ実行時の曜日enumクラス
     */
    private DayOfWeekKintai getTodayDayOfWeekKintai() {
        DayOfWeek todayDayOfWeek = LocalDate.now().getDayOfWeek();
        return DayOfWeekKintai.valueOf(String.valueOf(todayDayOfWeek));
    }
}
