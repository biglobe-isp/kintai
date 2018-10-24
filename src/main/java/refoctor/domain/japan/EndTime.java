package refoctor.domain.japan;

import refoctor.domain.japan.Form.End;
import refoctor.domain.japan.Form.EndHour;
import refoctor.domain.japan.Form.EndMinutes;

public class EndTime {
    private final End end;
    private final EndHour endHour;
    private final EndMinutes endMinutes;

    public EndTime(End end, EndHour endHour, EndMinutes endMinutes) {
        this.end = end;
        this.endHour = endHour;
        this.endMinutes = endMinutes;
    }

    public End getEnd() {
        return end;
    }

    public EndHour getEndHour() {
        return endHour;
    }

    public EndMinutes getEndMinutes() {
        return endMinutes;
    }

    public int getEndTotalMinutes() {
        return endHour.getValue() * 60 + endMinutes.getValue();
    }

}
