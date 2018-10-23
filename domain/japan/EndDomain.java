package domain.japan;


import domain.japan.ValueForm.End;
import domain.japan.ValueForm.EndH;
import domain.japan.ValueForm.EndM;

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

    public int getEndTotalMinutes() { return endH.getValue() * 60 + endM.getValue(); }

}
