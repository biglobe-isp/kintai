package com.naosim.dddwork.kintai.api.request;

import com.naosim.dddwork.kintai.api.settings.DataStorePolicy;
import com.naosim.dddwork.kintai.api.feature.input.InputRequestProcessor;
import com.naosim.dddwork.kintai.api.feature.total.TotalRequestProcessor;

import java.util.Arrays;


/**
 * リクエストたち
 */
public enum Request {

    INPUT {
        @Override
        public void execute(RequestOperands operands, DataStorePolicy dataStorePolicy) {

            final InputRequestProcessor processor = new InputRequestProcessor(dataStorePolicy);
            processor.execute(operands);
        }
    },

    TOTAL {
        @Override
        public void execute(RequestOperands operands, DataStorePolicy dataStorePolicy) {

            final TotalRequestProcessor processor = new TotalRequestProcessor(dataStorePolicy);
            processor.execute(operands);
        }
    },

    ;


    public abstract void execute(RequestOperands operands, DataStorePolicy dataStorePolicy);



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
