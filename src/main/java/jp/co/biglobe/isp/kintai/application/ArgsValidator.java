package jp.co.biglobe.isp.kintai.application;

public class ArgsValidator {

    public Method valid(String args[]) {
        if (args.length < 1) {
            throw new RuntimeException("引数が足りません");
        }

        String methodType = args[0];

        if ("input".equals(methodType)) {
            if (args.length < 4) {
                throw new RuntimeException("引数が足りません");
            }
            return Method.INPUT;
        } else if ("total".equals(methodType)) {
            if (args.length < 2) {
                throw new RuntimeException("引数が足りません");
            }
            return Method.TOTAL;
        } else {
            throw new RuntimeException("methodTypeが不正です");
        }
    }
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
