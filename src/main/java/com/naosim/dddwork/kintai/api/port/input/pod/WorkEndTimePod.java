package com.naosim.dddwork.kintai.api.port.input.pod;

import com.google.common.base.Verify;
import com.naosim.dddwork.kintai.domain.model.foundation.time.clock.WorkEndTime;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * ［作業終了時刻］ポッド
 */
public class WorkEndTimePod {

    private static final Pattern PATTERN = Pattern.compile("^(?<hour>[0-9]{2})(?<minute>[0-9]{2})$");
    private static final String NAME = "退勤時刻";
    private static final String MESSAGE = String.format("%sは HHmmフォーマットで入力してください．[入力値=${rawString}", NAME);

    private final String _rawString;

    final WorkEndTime endTime;


    public static WorkEndTimePod of(String rawString) {
        return new WorkEndTimePod(rawString);
    }

    public WorkEndTimePod(String rawString) {

        Verify.verifyNotNull(rawString, "%sが nullです。", NAME);

        _rawString = rawString;
        endTime = _parse(rawString);
    }

    private WorkEndTime _parse(String rawString) {

        final Matcher m = patternMatcherFor(rawString);

        final String hour = m.group("hour");
        final String minute = m.group("minute");

        return WorkEndTime.of(
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

    public WorkEndTime domainObject() {
        return endTime;
    }
}
