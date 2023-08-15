package kintai.domain;


import lombok.Value;

import java.util.List;

@Value
public class MonthTotalWorkingTime {
    MonthTotalWorkMinutes monthTotalWorkMinutes;
    MonthTotalOverWorkMinutes monthTotalOverWorkMinutes;



    public static MonthTotalWorkingTime totalTime(List<WorkingDateTotalRecord> workingDateTotalRecordList){
        int totalWorkMinutes = workingDateTotalRecordList.stream()
                .mapToInt(total -> total.getWorkMinutes().getWorkMinutes()).sum();
        int totalOverWorkMinutes = workingDateTotalRecordList.stream()
                .mapToInt(total -> total.getOverWorkMinutes().getOverWorkMinutes()).sum();

        /*for(WorkingDateTotalRecord total : workingDateTotalRecordList){
            totalWorkMinutes     += total.getWorkMinutes().getWorkMinutes();
            totalOverWorkMinutes += total.getOverWorkMinutes().getOverWorkMinutes();
        }*/
        return new MonthTotalWorkingTime(new MonthTotalWorkMinutes(totalWorkMinutes),new MonthTotalOverWorkMinutes(totalOverWorkMinutes));
    }

}
