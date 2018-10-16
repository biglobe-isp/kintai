package datasource;

import domain.*;

public class FileOutDatasource implements FileOutInterface {

//    public void kintaiOutPut(String date, String start, String end, int workMinutes, int overWorkMinutes, String now) {
//        FileOutInterface.super.kintaiOutPut(date, start, end, workMinutes, overWorkMinutes, now);
//    }
//
//    public void totalOutPut(String yearMonth) {
//        FileOutInterface.super.totalOutPut(yearMonth);
//    }
    public void kintaiOutPut(DateDomain dd, StartDomain sd, EndDomain ed, WorkMinutesDomain wd) {
        FileOutInterface.super.kintaiOutPut(dd, sd, ed, wd);
    }

    public void totalOutPut(DateDomain dd) {
        FileOutInterface.super.totalOutPut(dd);
    }

}
