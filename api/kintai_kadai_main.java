package api;

import domain.DateAndTimeRepository;
import service.DatetimeService;
import datasource.FileOutDatasource;

public class kintai_kadai_main {

    public static void main(String[] args) {
        DatetimeService datetimeService = new DatetimeService();
        FileOutDatasource fileOutDatasource = new FileOutDatasource();
        if ("input".equals(args[0])) {
            if (args.length < 4) {
                throw new RuntimeException("引数が足りません");
            }

            DateAndTimeRepository dt = new DateAndTimeRepository(args[0], args[1], args[2], args[3]);
            datetimeService.input(dt, fileOutDatasource);

        } else if ("total".equals(args[0])) {
            if (args.length < 2) {
                throw new RuntimeException("引数が足りません");
            }

            DateAndTimeRepository dt = new DateAndTimeRepository(args[0], args[1]);
            datetimeService.total(dt, fileOutDatasource);

        } else {
            throw new RuntimeException("methodTypeが不正です");

        }


//        datetimeService.kintai(args, fileOutDatasource);



    }
}