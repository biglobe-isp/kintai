package jp.co.esumit.kintai.api;

import javax.xml.bind.ValidationException;

public class Validator {
    public void isValidArgs(String args[]) throws Exception {

        if (args.length < 2) {
            throw new RuntimeException("引数が足りません。");
        }

        if (!isValidMethodType(args[0])) {
            throw new ValidationException("methodTypeが不正です。");
        }
    }

    private boolean isValidMethodType(String methodType) {

        return methodType.equals(MethodType.INPUT)
                || methodType.equals(MethodType.TOTAL);
    }
}
