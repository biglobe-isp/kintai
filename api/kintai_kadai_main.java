package api;

import service.DatetimeService;

public class kintai_kadai_main {
    public static void main(String[] args) {
        DatetimeService datetimeService = new DatetimeService();
        datetimeService.kintai(args);

    }
}