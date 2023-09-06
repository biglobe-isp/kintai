package kintai.service;

import kintai.domain.TotalMonthlyHoursWorked.MonthTotalWorkingTime;
import kintai.domain.WorkingDateTotalRecord.WorkingDateTotalRecord;
import lombok.RequiredArgsConstructor;

import java.time.YearMonth;
import java.util.List;

@RequiredArgsConstructor
public class MonthTotalWorkingTimeService {
    private final WorkingDateTotalRecordRepository workingDateTotalRecordRepository;
    public MonthTotalWorkingTime total(YearMonth yearMonth){
        List<WorkingDateTotalRecord> workingDateTotalRecordList = workingDateTotalRecordRepository.findByMonth(yearMonth);
        //(今回モック化する内容はおそらく、参照月を渡したらその月のworkingTotalRecordが帰ってくるList）
        //このリストを使って、どうするか。→合計を出す。
        return MonthTotalWorkingTime.totalTime(workingDateTotalRecordList);
    }


}
