package com.naosim.dddwork.kintai.api.pod;

import com.google.common.base.Verify;
import com.naosim.dddwork.kintai.domain.core.type.time.component.TimeOfDay;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * ［終業時刻］ポッド
 */
public class EndTimePod {

    private static final Pattern PATTERN = Pattern.compile("^(?<hour>[0-9]{2})(?<minute>[0-9]{2})$");
    private static final String NAME = "退勤時刻";
    private static final String MESSAGE = String.format("%sは HHmmフォーマットで入力してください．[入力値=${rawString}", NAME);

    private final String _rawString;

    final TimeOfDay timeOfDay;


    public static EndTimePod of(String rawString) {
        return new EndTimePod(rawString);
    }

    public EndTimePod(String rawString) {

        Verify.verifyNotNull(rawString, "%sが nullです。", NAME);

        _rawString = rawString;
        timeOfDay = _parse(rawString);
    }

    private TimeOfDay _parse(String rawString) {

        final Matcher m = patternMatcherFor(rawString);

        final String hour = m.group("hour");
        final String minute = m.group("minute");

        return TimeOfDay.of(
                Integer.valueOf(hour),
                Integer.valueOf(minute));
    }

    private Matcher patternMatcherFor(String rawString) {

        final Matcher matcher = PATTERN.matcher(rawString);

        if (!matcher.matches()) {
            throw new IllegalArgumentException(MESSAGE.replace("${rawString}", rawString));
        }
        return matcher;
    }

    //TODO: EndTimeにする予定
    public TimeOfDay domainObject() {
        return timeOfDay;
    }
}
