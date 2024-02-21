package com.kintai.datasource.value.expansion.password;

import com.kintai.datasource.enums.DayOfWeekKintai;
import com.kintai.datasource.value.Password;
import com.kintai.exception.ValidatorException;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class CsvPassword extends Password {
    public CsvPassword(String password) throws ValidatorException {
        super(password);
    }

    @Override
    protected void validate(String password) throws ValidatorException {
        isRequired(password);
        matchPassword(password);
    }

    protected void matchPassword(String password) throws ValidatorException {
        DayOfWeekKintai todayDayOfWeek = getTodayDayOfWeekKintai();
        if(!todayDayOfWeek.getDayOfName().equals(password)) {
            throw new ValidatorException("パスワードが一致しません。");
        }
    }

    private DayOfWeekKintai getTodayDayOfWeekKintai() {
        DayOfWeek todayDayOfWeek = LocalDate.now().getDayOfWeek();
        return DayOfWeekKintai.valueOf(String.valueOf(todayDayOfWeek));
    }
}
