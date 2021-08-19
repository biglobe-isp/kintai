package jp.co.esumit.kintai.api;

import java.util.Arrays;

public class InputPattern {
    public boolean isVacation(String inputArgs[]) {
        return Arrays.asList(inputArgs).contains("v");
    }

    public boolean isAmOff(String inputArgs[]) {
        return Arrays.asList(inputArgs).contains("am")
                && Arrays.stream(inputArgs).anyMatch(x -> x.startsWith(Prefix.END));
    }

    public boolean isPmOff(String inputArgs[]) {
        return Arrays.asList(inputArgs).contains("pm")
                && Arrays.stream(inputArgs).anyMatch(x -> x.startsWith(Prefix.START));
    }
}
