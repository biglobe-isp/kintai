package service;

import domain.CalculateDomein;
import domain.DateAndTimeRepository;
import domain.FileOutInterface;


public class DatetimeService {

    //    public void kintai(String[] args, FileOutInterface fileOutDatasource) {
//        CalculateDomein calculateDomein = new CalculateDomein();
//        if ("input".equals(args[0])) {
//
//            calculateDomein.input(args, fileOutDatasource);
//
//        } else if ("total".equals(args[0])) {
//
//            calculateDomein.total(args, fileOutDatasource);
//
//        } else {
//            throw new RuntimeException("methodTypeが不正です");
//        }
//    }
    CalculateDomein calculateDomein = new CalculateDomein();

    public void input(DateAndTimeRepository dt, FileOutInterface fileOutDatasource) {

        calculateDomein.input(dt, fileOutDatasource);

    }

    public void total(DateAndTimeRepository dt, FileOutInterface fileOutDatasource) {

        calculateDomein.total(dt, fileOutDatasource);

    }

}
