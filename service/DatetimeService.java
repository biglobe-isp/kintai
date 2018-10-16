package service;

import datasource.FileOutDatasource2;
import domain.CalculateDomein;


public class DatetimeService {

//    public void kintai(String[] args) {
//        CalculateDomein calculateDomein = new CalculateDomein();
//        if ("input".equals(args[0])) {
//
//            calculateDomein.input(args);
//
//        } else if ("total".equals(args[0])) {
//
//            calculateDomein.total(args);
//
//        } else {
//            throw new RuntimeException("methodTypeが不正です");
//        }
//    }

    public void kintai(String[] args, FileOutDatasource2 fileOutDatasource2) {
        CalculateDomein calculateDomein = new CalculateDomein();
        if ("input".equals(args[0])) {

            calculateDomein.input(args, fileOutDatasource2);

        } else if ("total".equals(args[0])) {

            calculateDomein.total(args, fileOutDatasource2);

        } else {
            throw new RuntimeException("methodTypeが不正です");
        }
    }
}
