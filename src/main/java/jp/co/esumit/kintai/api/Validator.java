package jp.co.esumit.kintai.api;

import javax.xml.bind.ValidationException;

public class Validator {
    public void isValidArgs(String args[]) throws Exception {

        if (args.length < 2) {
            throw new RuntimeException("引数が足りません。");
        }
    }
}
