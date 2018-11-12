package refactor.datasource.entity;

import java.util.HashMap;


public class WorkMinutesData {
    private HashMap totalWorkMinutes = new HashMap<String, Integer>();

    private HashMap totalOverWorkMinutes = new HashMap<String, Integer>();

    public HashMap getTotalWorkMinutes() {
        return totalWorkMinutes;
    }

    public void setTotalWorkMinutes(HashMap totalWorkMinutes) {
        this.totalWorkMinutes = totalWorkMinutes;
    }

    public HashMap getTotalOverWorkMinutes() {
        return totalOverWorkMinutes;
    }

    public void setTotalOverWorkMinutes(HashMap totalOverWorkMinutes) {
        this.totalOverWorkMinutes = totalOverWorkMinutes;
    }
}
