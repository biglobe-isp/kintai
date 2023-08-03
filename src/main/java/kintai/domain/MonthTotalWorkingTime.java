package kintai.domain;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MonthTotalWorkingTime {
    private final MonthTotalWorkMinutes monthTotalWorkMinutes;
    private final MonthTotalOverWorkMinutes monthTotalOverWorkMinutes;



    public static MonthTotalWorkingTime totalTime(List<WorkingDateTotalRecord> workingDateTotalRecords){

        return new MonthTotalWorkingTime(new MonthTotalWorkMinutes(0),new MonthTotalOverWorkMinutes(0));
    }

}
