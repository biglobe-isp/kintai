package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class WorkEndTime {
    @Getter
    Integer value;

    public WorkEndTime(Integer value){
        this.value = value;
    }

}