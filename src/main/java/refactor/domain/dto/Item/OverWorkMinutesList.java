package refactor.domain.dto.Item;

import java.util.HashMap;

public class OverWorkMinutesList {

    private HashMap overWorkMinutesList;

    public OverWorkMinutesList(HashMap totalOverWorkMinutes) {
        this.overWorkMinutesList = totalOverWorkMinutes;
    }

    public int getTotalOverWorkMinutes(DateItem date) {
            return (int)overWorkMinutesList.get(date);
        }

}
