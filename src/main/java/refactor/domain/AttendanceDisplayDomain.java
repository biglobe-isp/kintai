package refactor.domain;

import refactor.domain.dto.Item.DateItem;
import refactor.domain.dto.WorkAndOverWorkMinutesList;
import refactor.domain.repository.AttendanceRepositorySelect;
import refactor.domain.dto.DisplayAttendanceEvent;
import refactor.domain.dto.OutPutTotalData;

import java.util.Set;

public class AttendanceDisplayDomain {
    public OutPutTotalData totalAttendance(DisplayAttendanceEvent data, AttendanceRepositorySelect repository){
        WorkAndOverWorkMinutesList rtnData = repository.select(data);
        Set<DateItem> keySet = rtnData.makeKeyByWorkMinutes();

        int totalWorkMinutes = 0;
        int totalOverWorkMinutes = 0;
        for(DateItem key : keySet) {
            totalWorkMinutes += rtnData.getworkMinutes(key);
            totalOverWorkMinutes += rtnData.getOverWorkMinutes(key);
        }

        return new OutPutTotalData(totalWorkMinutes,totalOverWorkMinutes);
    }
}
