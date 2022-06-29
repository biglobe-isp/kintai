package jp.co.biglobe.isp.kintai.api;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.regex.Pattern;

/**
 * 勤怠入力入力値クラス.<br>
 * 勤怠入力の際の入力値 日付、開始時刻、終了時刻を保持します。
 * コンストラクタでは、入力値に対して就業規則に依らない範囲での妥当性チェックを行います。
 *
 */
record InputArgs(String attendanceDate, String attendanceStartTime, String attendanceEndTime) {
    // 時刻の文字列パターン */
    private static Pattern PATTERN_TIME = Pattern.compile("^([01]\\d|2[0-3])[0-5]\\d$");

    InputArgs(String attendanceDate, String attendanceStartTime, String attendanceEndTime) {
        try {
            DateTimeFormatter.BASIC_ISO_DATE.withResolverStyle(ResolverStyle.STRICT).parse(attendanceDate);
        } catch (DateTimeParseException dtp) {
            throw new RuntimeException("勤怠日付は数字8桁からなる、正しい年月日を入力してください。");
        }
        if (!PATTERN_TIME.matcher(attendanceStartTime).matches()) {
            throw new RuntimeException("勤怠開始時刻は数字4文字（0000から2359の範囲）を入力してください。");
        }
        if (!PATTERN_TIME.matcher(attendanceEndTime).matches()) {
            throw new RuntimeException("勤怠終了時刻は数字4文字（0000から2359の範囲）を入力してください。");
        }
        if (attendanceStartTime.compareTo(attendanceEndTime) > 0) {
            throw new RuntimeException("勤怠開始時刻は勤怠終了時刻より前の時刻を入力してください。");
        }

        this.attendanceDate = attendanceDate;
        this.attendanceStartTime = attendanceStartTime;
        this.attendanceEndTime = attendanceEndTime;
    }
}
