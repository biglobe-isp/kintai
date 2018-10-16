package service;

import domain.*;


public class DatetimeService {

    //    public void kintai(String[] args, FileOutInterface fileOutDatasource) {
//        CalculateDomain calculateDomain = new CalculateDomain();
//        if ("input".equals(args[0])) {
//
//            calculateDomain.input(args, fileOutDatasource);
//
//        } else if ("total".equals(args[0])) {
//
//            calculateDomain.total(args, fileOutDatasource);
//
//        } else {
//            throw new RuntimeException("methodTypeが不正です");
//        }
//    }

    CalculateDomain calculateDomain = new CalculateDomain();

    public void input(DateDomain dd, StartDomain sd, EndDomain ed, WorkMinutesDomain wd, FileOutInterface fo) {

        calculateDomain.input(dd, sd, ed, wd, fo);

    }

    public void total(DateDomain dd, FileOutInterface fileOutDatasource) {

        calculateDomain.total(dd, fileOutDatasource);

    }

}
