package com.naosim.dddwork.kintai.api.pod;

import com.google.common.base.Verify;
import com.naosim.dddwork.kintai.domain.model.foundation.date.AttendanceDate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * ［勤怠日付］ポッド
 */
public class AttendanceDatePod {

    private static final Pattern PATTERN = Pattern.compile("(?<year>[0-9]{4})(?<month>[0-9]{2})(?<dayOfMonth>[0-9]{2})");
    private static final String NAME = "勤怠日付";
    private static final String MESSAGE = String.format("%sは yyyyMMddフォーマットで入力してください．[入力値=${rawString}]", NAME);

    private final String _rawString;

    final AttendanceDate attendanceDate;


    public static AttendanceDatePod of(String rawString) {
        return new AttendanceDatePod(rawString);
    }

    public AttendanceDatePod(String rawString) {

        Verify.verifyNotNull(rawString, "%sが nullです。", NAME);

        _rawString = rawString;
        attendanceDate = _parse(rawString);
    }

    private AttendanceDate _parse(String rawString) {

        final Matcher m = patternMatcherFor(rawString);

        final String year = m.group("year");
        final String month = m.group("month");
        final String dayOfMonth = m.group("dayOfMonth");

        return AttendanceDate.of(year, month, dayOfMonth);
    }

    private Matcher patternMatcherFor(String rawString) {

        final Matcher matcher = PATTERN.matcher(rawString);

        if (!matcher.matches()) {
            throw new IllegalArgumentException(MESSAGE.replace("${rawString}", rawString));
        }
        return matcher;
    }

    public AttendanceDate domainObject() {
        return attendanceDate;
    }
}
