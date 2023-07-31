package kintai.domain;

import lombok.Value;

@Value
public class StartWork {
    String value;

    public StartWork(String value) {
        this.value = value;
    }

}