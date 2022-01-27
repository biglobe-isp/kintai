package src.com.naosim.dddwork.domain;

import src.com.naosim.dddwork.domain.*;

import java.util.Map;
import java.util.Set;

public class SubTotal {
    private KintaiRepository kintaiRepository;


    public int sumKintaiWorkMinutes(Map<WorkingDate, WorkTime> kintaiWorkMinutesMap) {
        int totalWorkMinutes = 0;

        Set<WorkingDate> keySet = kintaiWorkMinutesMap.keySet();
        for (WorkingDate key : keySet) {
            totalWorkMinutes += kintaiWorkMinutesMap.get(key).getValue();
        }

        return totalWorkMinutes;
    }
    public int sumKintaiOverWorkMinutes(Map<WorkingDate, OverTime> kintaiWorkMinutesMap) {
        int totalOverWorkMinutes = 0;

        Set<WorkingDate> keySet = kintaiWorkMinutesMap.keySet();
        for (WorkingDate key : keySet) {
            totalOverWorkMinutes += kintaiWorkMinutesMap.get(key).getValue();
        }

        return totalOverWorkMinutes;
    }
}






