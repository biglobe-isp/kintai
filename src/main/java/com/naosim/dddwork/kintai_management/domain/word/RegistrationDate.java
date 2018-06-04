package com.naosim.dddwork.kintai_management.domain.word;

import com.naosim.dddwork.kintai_management.domain.system.IsPresentCheckable;
import com.naosim.dddwork.kintai_management.domain.system.date.DateFormatter;
import com.naosim.dddwork.kintai_management.domain.system.date.DateParser;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import java.time.LocalDate;

/**
 * 登録年月日
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class RegistrationDate implements IsPresentCheckable {

    @Getter
    private final LocalDate value;

    public RegistrationDate(String str) {
        if (str != null) {
            value = DateParser.parse_yyyyMMdd(str);
        } else {
            value = null;
        }
    }

    public String getFormatValue() {
        if (isPresent()) {
            return DateFormatter.format_yyyyMMdd(value);
        }
        return null;
    }
}
