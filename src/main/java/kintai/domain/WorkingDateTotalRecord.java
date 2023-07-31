package kintai.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WorkingDateTotalRecord {
    WorkDay   workDay;
    StartWork startWork;
    EndWork   endWork;
    OverWorkMinutes overWorkMinutes ;

    WorkMinutes workMinutes;

}
