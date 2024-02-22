package com.kintai.datasource.value;

import com.kintai.exception.ValidatorException;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * 勤怠管理として終業時刻を管理するValue Object
 */
public class EndTime {
    // 終業時刻の値
    @Getter
    protected String endTime;

    /**
     * コンストラクタ
     * 引数の終業時刻のバリデーションチェックを実施し、異常を検知した場合は{@link ValidatorException}をスローします。
     * @param endTime 終業時刻
     * @throws ValidatorException 異常を検知した場合にスロー
     */
    public EndTime(String endTime) throws ValidatorException {
        validate(endTime);
        this.endTime = endTime;
    }

    /**
     * 終業時刻のバリデーションチェックを実施します。チェックで異常を検知した場合は{@link ValidatorException}をスローします。
     * @param endTime 終業時刻
     * @throws ValidatorException バリデーションチェックで異常を検知した場合にスロー
     */
    protected void validate(String endTime) throws ValidatorException {
        isRequired(endTime);
        isTime(endTime);
    }

    /**
     * 終業時刻の必須チェックをします。
     * @param endTime 終業時刻
     * @throws ValidatorException 終業時刻がnullや空文字。スペースのみ(半角、全角両方)の場合、{@link ValidatorException}をスローします。
     */
    protected void isRequired(String endTime) throws ValidatorException {
        if(!StringUtils.hasText(endTime)) {
            throw new ValidatorException("終業時刻は必須です。");
        }
    }

    /**
     * 時刻の形式チェックをします。
     * 時刻の形式が「HHmm」であるかチェックします。HHのため、時間は24時間制です。
     * @param endTime 終業時刻
     * @throws ValidatorException 指定した時刻の形式ではない場合、{@link ValidatorException}をスローします。
     */
    protected void isTime(String endTime) throws ValidatorException {
        try {
            /*
             * 勤務時刻はHHmmであるかチェック
             * 厳密にチェックするため、ResolverStyle.STRICTを設定
             */
            LocalTime.parse(endTime, DateTimeFormatter.ofPattern("HHmm").withResolverStyle(ResolverStyle.STRICT));
        } catch (DateTimeParseException e) {
            throw new ValidatorException("終業時刻の形式はHHmmでなければなりません。");
        }
    }

}
