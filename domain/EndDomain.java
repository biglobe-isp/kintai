package domain;


import domain.ValueForm.End;
import domain.ValueForm.EndH;
import domain.ValueForm.EndM;

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

    public int getEndTotalMVal() { return endH.getEndHVal() * 60 + endM.getEndMVal(); }

}
