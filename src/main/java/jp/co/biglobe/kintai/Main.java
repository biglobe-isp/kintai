package jp.co.biglobe.kintai;

import jp.co.biglobe.kintai.api.KintaiApi;

import java.time.LocalDateTime;

public class Main {

    private static String MethodType_Input = "input";
    private static String MethodType_Total = "total";

    private static int InputArgsIndex_MethodType = 0;
    private static int InputArgsIndex_Date = 1;
    private static int InputArgsIndex_StartTime = 2;
    private static int InputArgsIndex_EndTime = 3;

    private static int InputArgsIndex_YearMonth = 1;

    public static void main(String[] args) {
        try {
            if (args.length < 1) {
                throw new RuntimeException("引数が足りません");
            }

            String methodType = args[InputArgsIndex_MethodType];

            if (MethodType_Input.equals(methodType)) {
                if (args.length < 4) {
                    throw new RuntimeException("引数が足りません");
                }
                String date = args[InputArgsIndex_Date];
                String start = args[InputArgsIndex_StartTime];
                String end = args[InputArgsIndex_EndTime];
                String now = LocalDateTime.now().toString();

                KintaiApi.input(date, start, end, now);

            } else if (MethodType_Total.equals(methodType)) {
                String yearMonth = args[InputArgsIndex_YearMonth];
                if (args.length < 2) {
                    throw new RuntimeException("引数が足りません");
                }
                KintaiApi.total(yearMonth);

            } else {
                throw new RuntimeException("methodTypeが不正です");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}