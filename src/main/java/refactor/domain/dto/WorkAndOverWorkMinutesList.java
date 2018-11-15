package refactor.domain.dto;

import refactor.domain.dto.Item.DateItem;
import refactor.domain.dto.Item.OverWorkMinutesList;
import refactor.domain.dto.Item.WorkMinutesList;

import java.util.Set;

public class WorkAndOverWorkMinutesList {
    private final WorkMinutesList workMinutesList;

    private final OverWorkMinutesList overWorkMinutesList;

    public WorkAndOverWorkMinutesList(WorkMinutesList workMinutesList, OverWorkMinutesList overWorkMinutesList) {
        this.workMinutesList = workMinutesList;
        this.overWorkMinutesList = overWorkMinutesList;
    }

    public WorkMinutesList getWorkMinutesList() {
        return workMinutesList;
    }

    public OverWorkMinutesList getOverWorkMinutesList() {
        return overWorkMinutesList;
    }

    public Set<DateItem> makeKeyByWorkMinutes(){
        return workMinutesList.getTotalWorkMinutes().keySet();
    }

    public int getworkMinutes(DateItem date){
        return workMinutesList.getWrokMinutes(date);
    }

    public int getOverWorkMinutes(DateItem date){
        return overWorkMinutesList.getTotalOverWorkMinutes(date);
    }

}

