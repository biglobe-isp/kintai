package jp.co.esumit.kintai.domain.field;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class TargetDay {
    private final String value;

    public TargetDay(String value){
        this.valid(value);
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

    private void valid(String value){
        try {
            DateTimeFormatter.ofPattern("uuuuMMdd").withResolverStyle(ResolverStyle.STRICT)
                    .parse(value, LocalDate::from);
        } catch (Exception e) {
            throw new IllegalArgumentException("対象日付が不正値です。");
        }
    }
}

