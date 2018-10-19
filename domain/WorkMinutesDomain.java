package domain;

public class WorkMinutesDomain {

    private final int workMinutes;
    private final int overWorkMinutes;
    private int tmpWorkMinutes;

    public WorkMinutesDomain(StartDomain sd, EndDomain ed, DateDomain date) {

        this.tmpWorkMinutes = ed.getEndTotalMVal() - sd.getStartTotalMVal();

        if (ed.getEndH().getEndHVal() == 12) {
            this.tmpWorkMinutes -= ed.getEndM().getEndMVal();
        } else if (ed.getEndH().getEndHVal() >= 13) {
            this.tmpWorkMinutes -= 60;
        }
        if (date.getDate().isAfter()) {
            if (ed.getEndH().getEndHVal() == 15) {
                this.tmpWorkMinutes -= ed.getEndM().getEndMVal();
            } else if (ed.getEndH().getEndHVal() >= 16) {
                this.tmpWorkMinutes -= 60;
            }
        }
        if (ed.getEndH().getEndHVal() == 18) {
            this.tmpWorkMinutes -= ed.getEndM().getEndMVal();
        } else if (ed.getEndH().getEndHVal() >= 19) {
            this.tmpWorkMinutes -= 60;
        }

        if (ed.getEndH().getEndHVal() == 21) {
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
