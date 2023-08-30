package kintai.domain.LaborRegulations;

import lombok.Value;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Value
public class RestTime {
    LocalTime start;
    LocalTime end;


    public  RestTime (String s, String e){
        this.start = LocalTime.parse(s);
        this.end   = LocalTime.parse(e);
    }
    public RestTime parse(String s, String e){
        return new RestTime(s,e);
    }
    public String getStart(){
        return this.start.format(DateTimeFormatter.ISO_TIME);
    }
    public String getEnd(){
        return this.end.format(DateTimeFormatter.ISO_TIME);
    }
    public LocalTime getStartLocalTime(){
        return this.start;
    }
    public LocalTime getEndLocalTime(){
        return this.end;
    }

}
