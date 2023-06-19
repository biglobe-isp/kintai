package jp.co.biglobe.isp.kintai.application;

public class ArgsValidator {
    public void isValidInputArgs(String args[]) {
        if (args.length < 4) {
            throw new IllegalArgumentException("引数が足りません");
        }
    }

    public void isValidTotalArgs(String args[]) {
        if (args.length < 2) {
            throw new IllegalArgumentException("引数が足りません");
        }
    }
}
