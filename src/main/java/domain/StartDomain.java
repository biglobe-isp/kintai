package domain;

public class StartDomain {

    public String start;

    public int startH;

    public int startM;

    public StartDomain(String start) {
        this.start = start;
        this.startH = Integer.valueOf(start.substring(0, 2));
        this.startM = Integer.valueOf(start.substring(2, 4));
    }
}
