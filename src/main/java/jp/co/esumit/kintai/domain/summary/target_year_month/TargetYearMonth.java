package jp.co.esumit.kintai.domain.summary.target_year_month;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TargetYearMonth {
    String value;

    public TargetYearMonth(String value) {
        this.valid(value);
        this.value = value;
    }

    public String getValue() {
        return this.value;
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

