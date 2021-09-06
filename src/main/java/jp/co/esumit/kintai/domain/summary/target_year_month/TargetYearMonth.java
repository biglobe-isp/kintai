package jp.co.esumit.kintai.domain.summary.target_year_month;

import lombok.Value;

import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

@Value
public class TargetYearMonth {
    String value;

    public TargetYearMonth(String value) {
        valid(value);
        this.value = value;
    }

    private void valid(String value) {
        try {
            DateTimeFormatter.ofPattern("uuuuMM").
                    withResolverStyle(ResolverStyle.STRICT)
                    .parse(value);
        } catch (Exception e) {
            throw new IllegalArgumentException("対象年月が不正値です。");
        }
    }
}

