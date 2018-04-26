package com.naosim.dddwork.kintai_management.domain.word;

import com.naosim.dddwork.kintai_management.domain.system.IsPresentCheckable;
import jp.co.biglobe.lib.publication.date.DateFormatter;
import jp.co.biglobe.lib.publication.date.DateParser;
import lombok.Getter;

import java.time.LocalDate;

/**
 * 登録年月日
 */
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
