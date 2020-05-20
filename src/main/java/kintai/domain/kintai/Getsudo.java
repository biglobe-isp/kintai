package kintai.domain.kintai;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Getsudo {
    private String getsudo;
    
    public Getsudo(String getsudo) {
        this.setGetsudo(getsudo);
    }

    public String getGetsudo() {
        return this.getsudo;
    }

    private void setGetsudo(String getsudo) {
        this.getsudo = getsudo;
    }

    public static Getsudo getsudoOfTheDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM"); 
        return new Getsudo(date.format(formatter));
    }
}