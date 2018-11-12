package domain;

public class WorkMinutesDomain {

    public int workMinutes;

    public WorkMinutesDomain(String start, String end) {
        StartDomain startDomain = new StartDomain(start);
        EndDomain endDomain = new EndDomain(end);
        this.workMinutes = endDomain.endH * 60 + endDomain.endM - (startDomain.startH * 60 + startDomain.startM);
        // @todo メッソドを分ける
        if (endDomain.endH == 12) {
            this.workMinutes -= endDomain.endM;
        } else if (endDomain.endH >= 13) {
            this.workMinutes -= 60;
        }

        if (endDomain.endH == 18) {
            this.workMinutes -= endDomain.endM;
        } else if (endDomain.endH >= 19) {
            this.workMinutes -= 60;
        }

        if (endDomain.endH == 21) {
            this.workMinutes -= endDomain.endM;
        } else if (endDomain.endH >= 22) {
            this.workMinutes -= 60;
        }
    }

}
