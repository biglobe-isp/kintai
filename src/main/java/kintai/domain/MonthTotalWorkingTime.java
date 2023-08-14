package kintai.domain;

import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
public class MonthTotalWorkingTime {
    MonthTotalWorkMinutes monthTotalWorkMinutes;
    MonthTotalOverWorkMinutes monthTotalOverWorkMinutes;



    public static MonthTotalWorkingTime totalTime(List<WorkingDateTotalRecord> workingDateTotalRecordList){
        int totalWorkMinutes = 0;
        int totalOverWorkMinutes = 0;
        for(WorkingDateTotalRecord total : workingDateTotalRecordList){
            totalWorkMinutes     += total.getWorkMinutes().getWorkMinutes();
            totalOverWorkMinutes += total.getOverWorkMinutes().getOverWorkMinutes();
        }
        return new MonthTotalWorkingTime(new MonthTotalWorkMinutes(totalWorkMinutes),new MonthTotalOverWorkMinutes(totalOverWorkMinutes));
    }

}
