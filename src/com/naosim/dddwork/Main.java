package src.com.naosim.dddwork;

import src.com.naosim.dddwork.api.KintaiManage;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {

            if (args.length < 1) {
                throw new RuntimeException("引数が足りません");
            }
            String methodType = args[0];

            if ("input".equals(methodType)) {
                if (args.length < 4) {
                    throw new RuntimeException("引数が足りません");
                }

                KintaiManage api = new KintaiManage();
                api.Input(Arrays.copyOfRange(args, 1, 4));


            } else if ("total".equals(methodType)) {
                String yearMonth = args[1];
                if (args.length < 2) {
                    throw new RuntimeException("引数が足りません");
                }
                KintaiManage api = new KintaiManage();
                api.Total(yearMonth);

            } else {
                throw new RuntimeException("methodTypeが不正です");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}