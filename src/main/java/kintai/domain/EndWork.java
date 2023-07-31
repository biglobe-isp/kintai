package kintai.domain;

import lombok.Value;

@Value
public class EndWork {
    String theEndOfTime;

    public EndWork(String theEndOfTime){
        this.theEndOfTime = theEndOfTime;
    }
}
