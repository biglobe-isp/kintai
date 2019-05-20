package com.naosim.dddwork.kintai.api.port.input.pod;

import com.google.common.base.Verify;
import com.naosim.dddwork.kintai.domain.model.foundation.time.clock.WorkBeginTime;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * ［作業開始時刻］ポッド
 */
public class WorkBeginTimePod {

    private static final Pattern PATTERN = Pattern.compile("^(?<hour>[0-9]{2})(?<minute>[0-9]{2})$");
    private static final String NAME = "出勤時刻";
    private static final String MESSAGE = String.format("%sは HHmmフォーマットで入力してください．[入力値=${rawString}", NAME);

    private final String _rawString;

    final WorkBeginTime beginTime;


    public static WorkBeginTimePod of(String rawString) {
        return new WorkBeginTimePod(rawString);
    }

    public WorkBeginTimePod(String rawString) {

        Verify.verifyNotNull(rawString, "%sが nullです。", NAME);

        _rawString = rawString;
        beginTime = _parse(rawString);
    }

    private WorkBeginTime _parse(String rawString) {

        final Matcher m = patternMatcherFor(rawString);

        final String hour = m.group("hour");
        final String minute = m.group("minute");

        return WorkBeginTime.of(
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

    public WorkBeginTime domainObject() {
        return beginTime;
    }
}
