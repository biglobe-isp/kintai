package api;

import domain.*;
import service.DatetimeService;
import datasource.FileOutDatasource;

public class kintai_kadai_main {

//    public static void main(String[] args) {
//        DatetimeService datetimeService = new DatetimeService();
//        FileOutDatasource fileOutDatasource = new FileOutDatasource();
//        if ("input".equals(args[0])) {
//            if (args.length < 4) {
//                throw new RuntimeException("引数が足りません");
//            }
//
//            DateAndTimeDomain dt = new DateAndTimeDomain(args[0], args[1], args[2], args[3]);
//            datetimeService.input(dt, fileOutDatasource);
//
//        } else if ("total".equals(args[0])) {
//            if (args.length < 2) {
//                throw new RuntimeException("引数が足りません");
//            }
//
//            DateAndTimeDomain dt = new DateAndTimeDomain(args[0], args[1]);
//            datetimeService.total(dt, fileOutDatasource);
//
//        } else {
//            throw new RuntimeException("methodTypeが不正です");
//
//        }


//        datetimeService.kintai(args, fileOutDatasource);

    public static void main(String[] args) {
        DatetimeService datetimeService = new DatetimeService();
        FileOutDatasource fileOutDatasource = new FileOutDatasource();
        if ("input".equals(args[0])) {
            if (args.length < 4) {
                throw new RuntimeException("引数が足りません");
            }

            DateDomain dd = new DateDomain(args[1]);
            StartDomain sd = new StartDomain(args[2]);
            EndDomain ed = new EndDomain(args[3]);
            WorkMinutesDomain wm = new WorkMinutesDomain(sd, ed);
            datetimeService.input(dd, sd, ed, wm, fileOutDatasource);

        } else if ("total".equals(args[0])) {
            if (args.length < 2) {
                throw new RuntimeException("引数が足りません");
            }

            DateDomain dd = new DateDomain(args[1]);
            datetimeService.total(dd, fileOutDatasource);

        } else {
            throw new RuntimeException("methodTypeが不正です");

        }

    }
}