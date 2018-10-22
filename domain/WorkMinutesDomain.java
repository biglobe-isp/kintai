package domain;

public class WorkMinutesDomain {

    private final int workMinutes;
    private final int overWorkMinutes;

    public WorkMinutesDomain(StartDomain sd, EndDomain ed, DateDomain date) {


        int tmpWorkMinutes = ed.getEndTotalMVal() - sd.getStartTotalMVal();

        if (ed.getEndH().getValue() == 12) {
            tmpWorkMinutes -= ed.getEndM().getValue();
        } else if (ed.getEndH().getValue() >= 13) {
            tmpWorkMinutes -= 60;
        }
        if (date.isAfter20181115()) {
            if (ed.getEndH().getValue() == 15) {
                tmpWorkMinutes -= ed.getEndM().getValue();
            } else if (ed.getEndH().getValue() >= 16) {
                tmpWorkMinutes -= 60;
            }
        }
        if (ed.getEndH().getValue() == 18) {
            tmpWorkMinutes -= ed.getEndM().getValue();
        } else if (ed.getEndH().getValue() >= 19) {
            tmpWorkMinutes -= 60;
        }

        if (ed.getEndH().getValue() == 21) {
            tmpWorkMinutes -= ed.getEndM().getValue();
        } else if (ed.getEndH().getValue() >= 22) {
            tmpWorkMinutes -= 60;
        }

        this.workMinutes = tmpWorkMinutes;
        this.overWorkMinutes = Math.max(this.workMinutes - 8 * 60, 0);
    }


    public int getOverWorkMinutesVal() {
        return overWorkMinutes;
    }

    public int getWorkMinutesVal() {
        return workMinutes;
    }

}
