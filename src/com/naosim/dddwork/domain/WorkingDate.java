package src.com.naosim.dddwork.domain;

public class WorkingDate {
    private final String dateStr;

    public WorkingDate(String dateT) {
        this.dateStr = dateT;
    }
    public String toDateStr() {
        return this.dateStr;
    }

}
