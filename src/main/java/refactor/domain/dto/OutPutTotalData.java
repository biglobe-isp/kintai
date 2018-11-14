package refactor.api.form;

public class OutPutTotalData {
    private int totalWorkMinutes ;

    private int totalOverWorkMinutes;

    public int getTotalWorkMinutes() {
        return totalWorkMinutes;
    }

    public void setTotalWorkMinutes(int totalWorkMinutes) {
        this.totalWorkMinutes = totalWorkMinutes;
    }

    public int getTotalOverWorkMinutes() {
        return totalOverWorkMinutes;
    }

    public void setTotalOverWorkMinutes(int totalOverWorkMinutes) {
        this.totalOverWorkMinutes = totalOverWorkMinutes;
    }
}
