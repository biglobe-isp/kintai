package domain;

public class TimeVO {

    private final String startD;
    private final String endD;

    public TimeVO(String startD, String endD) {
        this.startD = startD;
        this.endD = endD;
    }

    public String getStartD() {
        return startD;
    }

    public String getEndD() {
        return endD;
    }

}
