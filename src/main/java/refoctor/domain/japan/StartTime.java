package refoctor.domain.japan;

import refoctor.domain.japan.Form.StartMinutes;
import refoctor.domain.japan.Form.Start;
import refoctor.domain.japan.Form.StartHour;

public class StartTime {
    private final Start start;
    private final StartHour startHour;
    private final StartMinutes startMinutes;

    public StartTime(Start start, StartHour startHour, StartMinutes startMinutes) {
        this.start = start;
        this.startHour = startHour;
        this.startMinutes = startMinutes;
    }

    public Start getStart() {
        return start;
    }

    public StartHour getStartHour() {
        return startHour;
    }

    public StartMinutes getStartMinutes() {
        return startMinutes;
    }

    public int getStartTotalMinutes() {
        return startHour.getValue() * 60 + startMinutes.getValue();
    }

}
