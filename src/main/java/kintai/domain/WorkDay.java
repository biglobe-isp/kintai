package kintai.domain;

import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Value
public class WorkDay {
    //yyyy-mm-dd形式で保持（iso-8601)
    LocalDate value;

    public WorkDay(String s){
        this.value = LocalDate.parse(s);
    }

    public static WorkDay parse(String s) {
        return new WorkDay(s);
    }

    public String getValue(){
        return value.format(DateTimeFormatter.ISO_DATE);
    }
    public LocalDate getLocalDate(){
        return value;
    }
    public String formatyyyyMMdd(){
        return value.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }
}