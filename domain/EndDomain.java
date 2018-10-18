package domain;


import domain.microForm.End;
import domain.microForm.EndH;
import domain.microForm.EndM;

public class EndDomain {

    private final End  end;
    private final EndH endH;
    private final EndM endM;
    
    public EndDomain(End end, EndH endH, EndM endM) {

        this.end = end;
        this.endH = endH;
        this.endM = endM;
    }
    
    public End  getEnd() {
        return end;
    }

    public EndH getEndH() {
        return endH;
    }

    public EndM getEndM() {
        return endM;
    }

    public int getEndTotalM() { return endH.getEndH() * 60 + endM.getEndM(); }

}
