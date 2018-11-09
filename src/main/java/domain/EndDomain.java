package domain;

public class EndDomain {

    public String end;

    public int endH;

    public int endM;

    public EndDomain(String end){
        this.end = end;
        this.endH = Integer.valueOf(end.substring(0, 2));
        this.endM = Integer.valueOf(end.substring(2, 4));
    }
}
