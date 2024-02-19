package com.kintai.datasource.value;

import com.kintai.exception.ValidatorException;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class EndTime {
    @Getter
    protected String endTime;

    public EndTime(String endTime) throws ValidatorException {
        validate(endTime);
        this.endTime = endTime;
    }

    protected void validate(String endTime) throws ValidatorException {
        isRequired(endTime);
        isTime(endTime);
    }

    protected void isRequired(String endTime) throws ValidatorException {
        if(!StringUtils.hasText(endTime)) {
            throw new ValidatorException("終業時刻は必須です。");
        }
    }

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
