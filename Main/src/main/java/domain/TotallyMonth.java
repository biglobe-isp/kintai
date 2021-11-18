package domain;

import lombok.Getter;

public class TotallyMonth {
    @Getter
    private String value;

    public TotallyMonth(String value){
        this.value = value;
    }
}
