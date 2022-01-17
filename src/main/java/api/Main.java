package api;

import datasource.CSVWorkRecordRepository;
import service.TotalWorkMinutesModel;
import service.WorkRecordApplicationService;

public final class Main {
    private static final WorkRecordApplicationService WORK_RECORD_APPLICATION_SERVICE
            = new WorkRecordApplicationService(new CSVWorkRecordRepository());

    public static void main(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("引数の数が足りません。");
        }

        MethodType methodType = MethodType.valueOf(args[0].toUpperCase());

        switch (methodType) {
            case INPUT:
                inputWorkRecord(args);
                break;
            case TOTAL:
                showTotalWorkMinutes(args);
                break;
        }
    }

    private static void inputWorkRecord(String[] args) {
        int[] date = parseDate(args[1]);
        int[] start = parseStartTime(args[2]);
        int[] end = parseEndTime(args[3]);

        WORK_RECORD_APPLICATION_SERVICE.inputWorkRecord(date[0], date[1], date[2], start[0], start[1], end[0], end[1]);
        System.out.println("勤怠情報を記録しました。");
    }

    private static void showTotalWorkMinutes(String[] args) {
        int[] yearMonth = parseYearMonth(args[1]);

        TotalWorkMinutesModel totalWorkMinutesModel = WORK_RECORD_APPLICATION_SERVICE.computeTotalWorkMinutes(yearMonth[0], yearMonth[1]);
        int totalWorkMinutes = totalWorkMinutesModel.getTotalWorkMinutes();
        int totalOverWorkMinutes = totalWorkMinutesModel.getTotalOverWorkMinutes();

        System.out.println("勤務時間: " + totalWorkMinutes / 60 + "時間" + totalWorkMinutes % 60 + "分");
        System.out.println("残業時間: " + totalOverWorkMinutes / 60 + "時間" + totalOverWorkMinutes % 60 + "分");
    }

    private static int[] parseDate(String dateAsText) {
        int year = Integer.parseInt(dateAsText.substring(0, 4));
        int month = Integer.parseInt(dateAsText.substring(4, 6));
        int day = Integer.parseInt(dateAsText.substring(6, 8));
        return new int[]{year, month, day};
    }

    private static int[] parseStartTime(String startTimeAsText) {
        int startHour = Integer.parseInt(startTimeAsText.substring(0, 2));
        int startMinutes = Integer.parseInt(startTimeAsText.substring(2, 4));
        return new int[]{startHour, startMinutes};
    }

    private static int[] parseEndTime(String endTimeAsText) {
        int endHour = Integer.parseInt(endTimeAsText.substring(0, 2));
        int endMinutes = Integer.parseInt(endTimeAsText.substring(2, 4));
        return new int[]{endHour, endMinutes};
    }

    private static int[] parseYearMonth(String yearMonthAsText) {
        int year = Integer.parseInt(yearMonthAsText.substring(0, 4));
        int month = Integer.parseInt(yearMonthAsText.substring(4, 6));
        return new int[]{year, month};
    }
}
