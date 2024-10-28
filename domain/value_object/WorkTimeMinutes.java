package com.naosim.dddwork.domain.value_object;

public class WorkTimeMinutes {
    private final Integer value;
    public WorkTimeMinutes(Integer value) {
        if(value < 0){
            throw new IllegalArgumentException("WorkTimeMinutes value can not be less than 0");
        }
        this.value = value;
    }
    public int getValue() {return value;}
}
