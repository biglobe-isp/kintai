package refactor.domain.dto.Item;

import java.util.HashMap;

public class TotalOverWorkMinutesList {

    private HashMap OverWorkMinutesList = new HashMap<String, Integer>();

    public TotalOverWorkMinutesList(HashMap totalOverWorkMinutes) {
        this.OverWorkMinutesList = totalOverWorkMinutes;
    }

    public HashMap getTotalOverWorkMinutes() {
            return OverWorkMinutesList;
        }

}
