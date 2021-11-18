package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class OverWorkTime {
    @Getter
    private Integer value;

    public OverWorkTime(int i){
        this.value = i;
    }

    public OverWorkTime calculate(WorkTime workTime) {
        Integer overWorkMinutes = Math.max(workTime.getValue() - 8 * 60, 0);
        value = overWorkMinutes;
        return new OverWorkTime(value);
    }
}