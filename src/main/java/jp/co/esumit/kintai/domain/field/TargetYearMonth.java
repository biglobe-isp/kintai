package jp.co.esumit.kintai.domain.field;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class TargetYearMonth {
    private final String value;

    public TargetYearMonth(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

    private void valid(String value){
        try {
            DateTimeFormatter.ofPattern("uuuuMM").withResolverStyle(ResolverStyle.STRICT)
                    .parse(value, LocalDate::from);
        } catch (Exception e) {
            throw new IllegalArgumentException("対象年月が不正値です。");
        }
    }
}

