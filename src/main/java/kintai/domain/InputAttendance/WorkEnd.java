package kintai.domain.InputAttendance;

import lombok.Value;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Value
public class WorkEnd {
    LocalTime value;

    public static WorkEnd parse(String s) {
        return new WorkEnd(LocalTime.parse(s));
    }

    public String formatHHmm() {
        return value.format(DateTimeFormatter.ofPattern("HHmm"));
    }
    public static WorkEnd parseHHmm(String s){
        return new WorkEnd(LocalTime.parse(s,DateTimeFormatter.ofPattern("HHmm")));
    }
    //09:30:20の形
    public String getValue(){
        return value.format(DateTimeFormatter.ISO_TIME);
    }

    public LocalTime getLocalTime(){
        return value;
    }
}
