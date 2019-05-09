package com.naosim.dddwork.kintai.api;

import com.naosim.dddwork.kintai.service.command.DailyWorkTimeRegistration;
import com.naosim.dddwork.kintai.service.query.MonthlyTotalWorkedTimeQuery;

import java.util.Arrays;

public enum Request {

    INPUT {
        @Override
        public void execute(String[] args) {

            final DailyWorkTimeRegistration service = new DailyWorkTimeRegistration();
            service.execute(args);
        }
    },

    TOTAL {
        @Override
        public void execute(String[] args) {

            final MonthlyTotalWorkedTimeQuery service = new MonthlyTotalWorkedTimeQuery();
            service.execute(args);

        }
    },

    ;


    public abstract void execute(String[] args);



    public String lowercaseName() {
        return name().toLowerCase();
    }



    public static String validRequestValues() {

        return Arrays.stream(values()).parallel().reduce(
                "",
                (validValues, request) -> validValues.isEmpty() ? request.lowercaseName() : validValues + ", " + request.lowercaseName(),
                (lhsValidValues, rhsValidValues) -> lhsValidValues + ", " + rhsValidValues
        );
    }
}
