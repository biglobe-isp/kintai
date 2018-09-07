package kintai.domain;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SyukkinDate {

    private String value;

    public SyukkinDate(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getYearMonth() {
        return value.substring(0,6);
    }

    public LocalDate getLocaldate() {
        return LocalDate.parse(value, DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    public boolean isLocalDate() {
        DateFormat format=new SimpleDateFormat("yyyyMMdd");
        try {
            format.parse(value);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
