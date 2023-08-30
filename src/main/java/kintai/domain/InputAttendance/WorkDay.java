package kintai.domain.InputAttendance;

import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Value
public class WorkDay {
    LocalDate value;

    public static WorkDay parse(String s) {
        return new WorkDay(LocalDate.parse(s));
    }

    public String getValue(){
        return value.format(DateTimeFormatter.ISO_DATE);
    }
    public LocalDate getLocalDate(){
        return value;
    }
    public String formatyyyyMMdd(){
        return value.format(DateTimeFormatter.BASIC_ISO_DATE);
    }
    public static WorkDay parseyyyyMMdd(String s){
        return new WorkDay(LocalDate.parse(s,DateTimeFormatter.BASIC_ISO_DATE));
    }
    //LocalDateでparseして日付文字列をLocalDate型にする。
    //型変換したLocalDate型の日付文字列をISOLocalDateの文字列に変換
    /*public WorkDay formatLocalDate(String s){
        LocalDate parsedDate = LocalDate.parse(s,DateTimeFormatter.BASIC_ISO_DATE);
        String formattedDate = parsedDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        return WorkDay.parse(formattedDate);
    }*/
}