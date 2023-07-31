package kintai.domain;

import lombok.Value;

@Value
public class WorkDay {
    String value;
    public WorkDay(String value){
        this.value = value;
    }

}
