package domain;


import domain.ValueForm.Start;
import domain.ValueForm.StartH;
import domain.ValueForm.StartM;

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

    public int getStartTotalMVal() { return startH.getStartHVal() * 60 + startM.getStartMVal(); }

}
