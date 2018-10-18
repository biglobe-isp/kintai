package domain;


public class StartDomain {

    private final String  start;
    private final int startH;
    private final int startM;

    public StartDomain(String start, int startH, int startM) {

        this.start = start;
        this.startH = startH;
        this.startM = startM;
    }

    public String  getStart() {
        return start;
    }

    public int getStartH() {
        return startH;
    }

    public int getStartM() {
        return startM;
    }

    public int getStartTotalM() { return startH * 60 + startM; }

}
