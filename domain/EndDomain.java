package domain;


public class EndDomain {

    private final String end;
    private final int endH;
    private final int endM;

    public EndDomain(String end) {
        this.end = end;
        this.endH = Integer.valueOf(this.end.substring(0, 2));
        this.endM = Integer.valueOf(this.end.substring(2, 4));
    }

    public String getEnd() {
        return end;
    }

    public int getEndH() { return endH; }

    public int getEndM() {
        return endM;
    }

    public int getEndTotalM() {
        return endH * 60 + endM;
    }


}
