package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class WorkStartTime {
    @Getter
    Integer value;

    public WorkStartTime(Integer value){
        this.value = value;
    }
}
