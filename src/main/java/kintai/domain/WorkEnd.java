package kintai.domain;

import lombok.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Value
public class WorkEnd {
    LocalTime value;

    public static WorkEnd parse(String s) {
        return new WorkEnd(s);
    }

    public String formatHHmm() {
        return value.format(DateTimeFormatter.ofPattern("HHmm"));
    }
    //09:30:20の形
    public String getValue(){
        return value.format(DateTimeFormatter.ISO_TIME);
    }
    public WorkEnd(String s){
        this.value = LocalTime.parse(s);
    }
    public LocalTime getLocalTime(){
        return value;
    }
}
