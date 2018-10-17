package domain;


public class StartDomain {

    private final String start;
    private final int startH;
    private final int startM;


    public StartDomain(String start) {

        this.start = start;
        this.startH = Integer.valueOf(this.start.substring(0, 2));
        this.startM = Integer.valueOf(this.start.substring(2, 4));
    }


    public String getStart() {
        return start;
    }

    public int getStartM() {
        return startM;
    }

    public int getStartH() {
        return startH;
    }

    public int getStartTotalM() {
        return startH * 60 + startM;
    }



}
