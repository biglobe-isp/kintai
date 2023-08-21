package kintai.domain;

import lombok.Value;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Value
public class WorkStart {
    LocalTime value;

    public static WorkStart parse(String s) {
        return new WorkStart(LocalTime.parse(s));
    }

    //hh:mm:ssで持っているので、formatする(HHmm)
    public String formatHHmm(){
        return value.format(DateTimeFormatter.ofPattern("HHmm"));
    }
    public LocalTime getLocalTime(){
        return this.value;
    }
    public String formatISOTime(){
        return value.format(DateTimeFormatter.ISO_TIME);
    }

}