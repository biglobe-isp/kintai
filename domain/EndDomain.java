package domain;


public class EndDomain {

    private final String  end;
    private final int endH;
    private final int endM;
    
    public EndDomain(String end, int endH, int endM) {

        this.end = end;
        this.endH = endH;
        this.endM = endM;
    }
    
    public String  getEnd() {
        return end;
    }

    public int getEndH() {
        return endH;
    }

    public int getEndM() {
        return endM;
    }

    public int getEndTotalM() { return endH * 60 + endM; }

}
