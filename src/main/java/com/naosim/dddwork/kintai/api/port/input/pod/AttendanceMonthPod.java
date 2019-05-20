package com.naosim.dddwork.kintai.api.port.input.pod;

import com.google.common.base.Verify;
import com.naosim.dddwork.kintai.domain.model.foundation.date.AttendanceYearMonth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * ［勤怠年月］ポッド
 */
public class AttendanceMonthPod {

    private static final Pattern PATTERN = Pattern.compile("^(?<year>[0-9]{4})(?<month>[0-9]{2})$");
    private static final String NAME = "勤怠月";
    private static final String MESSAGE = String.format("%sは yyyyMMフォーマットで入力してください．[入力値=${rawString}", NAME);

    private final String _rawString;

    private final AttendanceYearMonth _attendanceYearMonth;


    public static AttendanceMonthPod of(String rawString) {
        return new AttendanceMonthPod(rawString);
    }

    public AttendanceMonthPod(String rawString) {

        Verify.verifyNotNull(rawString, "%sが nullです。", NAME);

        _rawString = rawString;
        _attendanceYearMonth = _parse(rawString);
    }

    private AttendanceYearMonth _parse(String rawString) {

        final Matcher m = patternMatcherFor(rawString);

        final String year = m.group("year");
        final String month = m.group("month");

        return AttendanceYearMonth.of(year, month);
    }

    private Matcher patternMatcherFor(String rawString) {

        final Matcher matcher = PATTERN.matcher(rawString);

        if (!matcher.matches()) {
            throw new IllegalArgumentException(MESSAGE.replace("${rawString}", rawString));
        }
        return matcher;
    }

    public AttendanceYearMonth domainObject() {
        return _attendanceYearMonth;
    }
}
