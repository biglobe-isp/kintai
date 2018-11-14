package refactor.domain.dto.Item;

import java.util.Date;
import java.util.HashMap;

public class WorkMinutesList {
    private HashMap<DateItem,Integer> workMinutesList;

    public WorkMinutesList(HashMap totalWorkMinutes) {
        this.workMinutesList = totalWorkMinutes;
    }

    public HashMap getTotalWorkMinutes() {
        return workMinutesList;
    }

    public int getWrokMinutes(DateItem date){
        return (int)workMinutesList.get(date);
    }
}
