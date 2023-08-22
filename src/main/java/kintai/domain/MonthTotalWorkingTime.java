package kintai.domain;


import lombok.Value;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class MonthTotalWorkingTime {
    MonthTotalWorkMinutes monthTotalWorkMinutes;
    MonthTotalOverWorkMinutes monthTotalOverWorkMinutes;



    //指定した１月の勤務実績を参照し合計勤務、残業時間を返す。
    //自信の頭の中のコーディング以外をはじけるようにエラー処理を実装する。
    public static MonthTotalWorkingTime totalTime(List<WorkingDateTotalRecord> workingDateTotalRecordList){
//        String firstMonthValue = workingDateTotalRecordList.get(0).getWorkDay().getValue().substring(0,7);
//        for(WorkingDateTotalRecord totalOfDay : workingDateTotalRecordList){
//            if(!firstMonthValue.equals(totalOfDay.getWorkDay().getValue().substring(0, 7))){
//                throw new IllegalArgumentException("一か月の合計を参照します");
//            }
//        }
        int groupSize = workingDateTotalRecordList.stream()
                .collect(Collectors.groupingBy(r -> r.getWorkDay().getLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM")))).size();
        if(groupSize > 1 ){
            throw new IllegalArgumentException("一か月の合計を参照します");
        }

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
