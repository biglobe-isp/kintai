package refactor.domain.dto;

import java.util.HashMap;

public class WorkAndOverWorkMinutesList {
    private HashMap totalWorkMinutes = new HashMap<String, Integer>();

    public WorkAndOverWorkMinutesList(HashMap totalWorkMinutes) {
        this.totalWorkMinutes = totalWorkMinutes;
    }

    public HashMap getTotalWorkMinutes() {
        return totalWorkMinutes;
    }
}
