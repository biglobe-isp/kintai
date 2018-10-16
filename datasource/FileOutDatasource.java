package datasource;

import domain.DateAndTimeRepository;
import domain.FileOutInterface;

public class FileOutDatasource implements FileOutInterface {

//    public void kintaiOutPut(String date, String start, String end, int workMinutes, int overWorkMinutes, String now) {
//        FileOutInterface.super.kintaiOutPut(date, start, end, workMinutes, overWorkMinutes, now);
//    }
//
//    public void totalOutPut(String yearMonth) {
//        FileOutInterface.super.totalOutPut(yearMonth);
//    }
    public void kintaiOutPut(DateAndTimeRepository dt) {
        FileOutInterface.super.kintaiOutPut(dt);
    }

    public void totalOutPut(DateAndTimeRepository dt) {
        FileOutInterface.super.totalOutPut(dt);
    }

}
