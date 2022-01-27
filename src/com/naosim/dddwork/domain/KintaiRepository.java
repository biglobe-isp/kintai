package src.com.naosim.dddwork.domain;

import java.util.List;
import java.util.Map;

public interface KintaiRepository {
    void write(Regulation kintai) ;
    Map<WorkingDate, WorkTime> getTotalWorkMinutesMapOf(String yearMonth);
    Map<WorkingDate, OverTime> getTotalOverWorkMinutesMapOf(String yearMonth);

}