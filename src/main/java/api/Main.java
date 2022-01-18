package api;

import datasource.CSVWorkRecordRepository;
import service.TotalWorkMinutesModel;
import service.WorkRecordApplicationService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Main {
    private static final WorkRecordApplicationService WORK_RECORD_APPLICATION_SERVICE
            = new WorkRecordApplicationService(new CSVWorkRecordRepository());

    private static final Pattern DATE_PATTERN = Pattern.compile("-date:(?<year>\\d{4})(?<month>\\d{2})(?<day>\\d{2})");
    private static final Pattern START_TIME_PATTERN = Pattern.compile("-start:(?<hours>\\d{2})_(?<minutes>\\d{2})");
    private static final Pattern END_TIME_PATTERN = Pattern.compile("-end:(?<hours>\\d{2})_(?<minutes>\\d{2})");
    private static final Pattern YEAR_MONTH_PATTERN = Pattern.compile("(?<year>\\d{4})(?<month>\\d{2})");

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
        if (args.length != 4) {
            throw new IllegalArgumentException("引数はちょうど４つ必要です。");
        }
        int[] date = parseDate(args[1]);
        int[] start = parseStartTime(args[2]);
        int[] end = parseEndTime(args[3]);

        WORK_RECORD_APPLICATION_SERVICE.inputWorkRecord(date[0], date[1], date[2], start[0], start[1], end[0], end[1]);
        System.out.println("勤怠情報を記録しました。");
    }

    private static void showTotalWorkMinutes(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("引数はちょうど２つ必要です。");
        }
        int[] yearMonth = parseYearMonth(args[1]);

        TotalWorkMinutesModel totalWorkMinutesModel = WORK_RECORD_APPLICATION_SERVICE.computeTotalWorkMinutes(yearMonth[0], yearMonth[1]);
        int totalWorkMinutes = totalWorkMinutesModel.getTotalWorkMinutes();
        int totalOverWorkMinutes = totalWorkMinutesModel.getTotalOverWorkMinutes();

        int minuetsPerHour = 60;
        System.out.println("勤務時間: " + totalWorkMinutes / minuetsPerHour + "時間" + totalWorkMinutes % minuetsPerHour + "分");
        System.out.println("残業時間: " + totalOverWorkMinutes / minuetsPerHour + "時間" + totalOverWorkMinutes % minuetsPerHour + "分");
    }

    private static int[] parseDate(String dateAsText) {
        Matcher dateMatcher = DATE_PATTERN.matcher(dateAsText);
        if (!dateMatcher.matches()) {
            throw new IllegalArgumentException("年月日は-date:YYYYMMDDの形式で入力してください。");
        }
        int year = Integer.parseInt(dateMatcher.group("year"));
        int month = Integer.parseInt(dateMatcher.group("month"));
        int day = Integer.parseInt(dateMatcher.group("day"));
        return new int[]{year, month, day};
    }

    private static int[] parseStartTime(String startTimeAsText) {
        Matcher startTimeMatcher = START_TIME_PATTERN.matcher(startTimeAsText);
        if (!startTimeMatcher.matches()) {
            throw new IllegalArgumentException("勤務開始時刻は-start:hh_mmの形式で入力してください。");
        }
        int startHour = Integer.parseInt(startTimeMatcher.group("hours"));
        int startMinutes = Integer.parseInt(startTimeMatcher.group("minutes"));
        return new int[]{startHour, startMinutes};
    }

    private static int[] parseEndTime(String endTimeAsText) {
        Matcher endTimeMatcher = END_TIME_PATTERN.matcher(endTimeAsText);
        if (!endTimeMatcher.matches()) {
            throw new IllegalArgumentException("勤務終了時刻は-end:hh_mmの形式で入力してください。");
        }
        int endHour = Integer.parseInt(endTimeMatcher.group("hours"));
        int endMinutes = Integer.parseInt(endTimeMatcher.group("minutes"));
        return new int[]{endHour, endMinutes};
    }

    private static int[] parseYearMonth(String yearMonthAsText) {
        Matcher yearMonthMatcher = YEAR_MONTH_PATTERN.matcher(yearMonthAsText);
        if (!yearMonthMatcher.matches()) {
            throw new IllegalArgumentException("年月はYYYYMMの形式で入力してください。");
        }
        int year = Integer.parseInt(yearMonthMatcher.group("year"));
        int month = Integer.parseInt(yearMonthMatcher.group("month"));
        return new int[]{year, month};
    }
}