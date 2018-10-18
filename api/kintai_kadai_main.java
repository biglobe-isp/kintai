package api;

import service.DatetimeService;
import datasource.FileOutDatasource;

public class kintai_kadai_main {

    public static void main(String[] args) {
        DatetimeService datetimeService = new DatetimeService();
        FileOutDatasource fd = new FileOutDatasource();
        CheckForm cf = new CheckForm(args);
        if (MethodType.INPUT.equals(cf.getMethodType())) {

            datetimeService.input(cf.getDate(), cf.getStart(), cf.getEnd(), cf.getWork(), fd);

        } else if (MethodType.TOTAL.equals(cf.getMethodType())) {

            datetimeService.total(cf.getDate(), fd);
        }

    }
}