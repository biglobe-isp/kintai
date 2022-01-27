package src.com.naosim.dddwork.domain;

public class ResultTotal {
    private final int totalWorkMinutes;
    private final int totalOverWorkMinutes;

    public ResultTotal(int totalWorkMinutes, int totalOverWorkMinutes) {
        this.totalWorkMinutes = totalWorkMinutes;
        this.totalOverWorkMinutes = totalOverWorkMinutes;

    }
    public int getTotalWork() {
        return this.totalWorkMinutes;
    }
    public int getTotalOverWork() {
        return this.totalOverWorkMinutes;
    }

}
