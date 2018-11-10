package domain;

public class WorkMinutesDomain {

    public int workMinutes;

    public WorkMinutesDomain(String start, String end) {
        StartDomain startDomain = new StartDomain(start);
        EndDomain endDomain = new EndDomain(end);
        this.workMinutes = endDomain.endH * 60 + endDomain.endM - (startDomain.startH * 60 + startDomain.startM);
        // @todo メッソドを分ける
        if (endDomain.endH == 12) {
            workMinutes -= endDomain.endM;
        } else if (endDomain.endH >= 13) {
            workMinutes -= 60;
        }

        if (endDomain.endH == 18) {
            workMinutes -= endDomain.endM;
        } else if (endDomain.endH >= 19) {
            workMinutes -= 60;
        }

        if (endDomain.endH == 21) {
            workMinutes -= endDomain.endM;
        } else if (endDomain.endH >= 22) {
            workMinutes -= 60;
        }
    }
}
