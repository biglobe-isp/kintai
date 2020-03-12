package com.naosim.dddwork.api.validator;

import com.google.common.base.Strings;
import com.naosim.dddwork.domain.TimePoint;
import com.naosim.dddwork.domain.attendance.WorkDay;
import com.naosim.dddwork.domain.monthlysummary.YearMonth;
import com.naosim.dddwork.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ParamConverter {

    public YearMonth convertYearMonth(String input) throws ValidationException {
        if (!isDateValid(input, EnumDateFormat.yyyyMM)) {
            throw new ValidationException("年月の形式がただしくありません");
        }
        return YearMonth.of(Integer.parseInt(input.substring(0, 4)),
                            Integer.parseInt(input.substring(4, 6)));
    }

    public WorkDay convertWorkDay(String input) throws ValidationException {
        if (!isDateValid(input, EnumDateFormat.yyyyMMdd)) {
            throw new ValidationException("日付の形式がただしくありません");
        }
        return WorkDay.of(input);
    }

    public TimePoint convertTimePoint(String input) throws ValidationException {
        if (!isTimeValid(input)) {
            throw new ValidationException("時刻の形式がただしくありません");
        }
        return TimePoint.of(Integer.parseInt(input.substring(0, 2)),
                            Integer.parseInt(input.substring(2)));
    }

    private boolean isDateValid(String input, EnumDateFormat format) {
        try {
            if (input.length() != format.name().length()) {
                return false;
            }

            SimpleDateFormat sdf = new SimpleDateFormat(format.name());
            sdf.setLenient(false);
            sdf.parse(input);

            return true;

        } catch(Exception ex) {
            return false;
        }
    }

    private boolean isTimeValid(String input) {
        if (Strings.isNullOrEmpty(input))
            return false;
        Pattern p = Pattern.compile("^([0-1][0-9]|[2][0-9])[0-5][0-9]$");
        Matcher m = p.matcher(input);
        if ( !m.find() )
            return false;
        return true;
    }
}
