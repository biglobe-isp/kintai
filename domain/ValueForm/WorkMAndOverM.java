package domain.ValueForm;


import domain.EndDomain;
import domain.StartDomain;

public class WorkMAndOverM {

    private final int workMinutes;
    private final int overWorkMinutes;
    private int tmpWorkMinutes;

    public WorkMAndOverM(StartDomain sd, EndDomain ed) {

        this.tmpWorkMinutes = ed.getEndTotalM() - sd.getStartTotalM();
        if (ed.getEndH().getEndHVal() == 12) {
            this.tmpWorkMinutes -= ed.getEndM().getEndMVal();
        } else if (ed.getEndH().getEndHVal() >= 13) {
            this.tmpWorkMinutes -= 60;
        } else if  (ed.getEndH().getEndHVal() == 18) {
            this.tmpWorkMinutes -= ed.getEndM().getEndMVal();
        } else if (ed.getEndH().getEndHVal() >= 19) {
            this.tmpWorkMinutes -= 60;
        } else if  (ed.getEndH().getEndHVal() == 21) {
            this.tmpWorkMinutes -= ed.getEndM().getEndMVal();
        } else if (ed.getEndH().getEndHVal() >= 22) {
            this.tmpWorkMinutes -= 60;
        }
        this.workMinutes = this.tmpWorkMinutes;
        this.overWorkMinutes = Math.max(this.workMinutes - 8 * 60, 0);
    }


    public int getOverWorkMinutesVal() {
        return overWorkMinutes;
    }

    public int getWorkMinutesVal() {
        return workMinutes;
    }

}
