package api;

import service.DatetimeService;
import datasource.FileOutDatasource2;

public class kintai_kadai_main {
//    public static void main(String[] args) {
//        DatetimeService datetimeService = new DatetimeService();
//        datetimeService.kintai(args);
//
//    }
    public static void main(String[] args) {
        DatetimeService datetimeService = new DatetimeService();

        FileOutDatasource2 fileOutDatasource2 = new FileOutDatasource2();
        datetimeService.kintai(args, fileOutDatasource2);

    }
}