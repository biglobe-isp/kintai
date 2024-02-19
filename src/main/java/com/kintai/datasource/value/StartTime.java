package com.kintai.datasource.value;

import com.kintai.exception.ValidatorException;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class StartTime {
    @Getter
    protected String startTime;

    public StartTime(String startTime) throws ValidatorException {
        validate(startTime);
        this.startTime = startTime;
    }

    protected void validate(String startTime) throws ValidatorException {
        isRequired(startTime);
        isTime(startTime);
    }

    protected void isRequired(String startTime) throws ValidatorException {
        if(!StringUtils.hasText(startTime)) {
            throw new ValidatorException("開始時刻は必須です。");
        }
    }

    protected void isTime(String startTime) throws ValidatorException {
        try {
            /*
             * 勤務時刻はHHmmであるかチェック
             * 厳密にチェックするため、ResolverStyle.STRICTを設定
             */
            LocalTime.parse(startTime, DateTimeFormatter.ofPattern("HHmm").withResolverStyle(ResolverStyle.STRICT));
        } catch (DateTimeParseException e) {
            throw new ValidatorException("開始時刻の形式はHHmmでなければなりません。");
        }
    }
}
