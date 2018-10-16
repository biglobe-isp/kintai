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


    public EndDomain(String methodType, String date) {
        this.end = null;
        this.endH = 0;
        this.endM = 0;
    }


    public String getEnd() {
        return end;
    }

    public int getEndH() {
        return endH;
    }

    public int getEndM() {
        return endM;
    }
}
