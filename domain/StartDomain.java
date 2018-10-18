package domain;


import domain.microForm.Start;
import domain.microForm.StartH;
import domain.microForm.StartM;

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

    public int getStartTotalM() { return startH.getStartH() * 60 + startM.getStartM(); }

}
