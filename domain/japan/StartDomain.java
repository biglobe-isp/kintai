package domain.japan;


import domain.japan.ValueForm.Start;
import domain.japan.ValueForm.StartH;
import domain.japan.ValueForm.StartM;

public class StartDomain {

    private final Start  start;
    private final StartH startH;
    private final StartM startM;

    public StartDomain(Start start, StartH startH, StartM startM) {

        this.start = start;
        this.startH = startH;
        this.startM = startM;
    }

    public Start  getStart() {
        return start;
    }

    public StartH getStartH() {
        return startH;
    }

    public StartM getStartM() {
        return startM;
    }

    public int getStartTotalMinutes() { return startH.getValue() * 60 + startM.getValue(); }

}
