package domain;

import lombok.Getter;

public class WorkDay {
    @Getter
    private Integer value;

    public WorkDay(Integer value){
        this.value = value;
    }
}

