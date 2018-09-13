package jp.co.biglobe.kintai;

import jp.co.biglobe.kintai.domain.KintaiManager;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        try {
            if (args.length < 1) {
                throw new RuntimeException("引数が足りません");
            }
            String methodType = args[0];
            KintaiManager manager = new KintaiManager();

            if ("input".equals(methodType)) {
                if (args.length < 4) {
                    throw new RuntimeException("引数が足りません");
                }
                String date = args[1];
                String start = args[2];
                String end = args[3];
                String now = LocalDateTime.now().toString();

                manager.input(date, start, end, now);

            } else if ("total".equals(methodType)) {
                String yearMonth = args[1];
                if (args.length < 2) {
                    throw new RuntimeException("引数が足りません");
                }

                manager.total(yearMonth);

            } else {
                throw new RuntimeException("methodTypeが不正です");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}