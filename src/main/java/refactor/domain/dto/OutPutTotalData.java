package refactor.domain.dto;

public class OutPutTotalData {
    private final int totalWorkMinutes ;

    private final int totalOverWorkMinutes;

    public OutPutTotalData(int totalWorkMinutes, int totalOverWorkMinutes) {
        this.totalWorkMinutes = totalWorkMinutes;
        this.totalOverWorkMinutes = totalOverWorkMinutes;
    }

    public int getTotalWorkMinutes() {
        return totalWorkMinutes;
    }

    public int getTotalOverWorkMinutes() {
        return totalOverWorkMinutes;
    }
}
