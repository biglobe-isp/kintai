package com.naosim.dddwork.api.validator;

import lombok.Value;

import java.text.SimpleDateFormat;

@Value(staticConstructor = "of")
public class DateValidator {
    String value;

    public boolean isValid(EnumDateFormat format) {
        if (value.length() != format.name().length()) {
            return false;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format.name());
            sdf.setLenient(false);
            sdf.parse(value);

            return true;

        } catch(Exception ex) {
            return false;
        }
    }
}
