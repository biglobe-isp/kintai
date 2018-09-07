package kintai;

import kintai.api.KintaiAggregateApi;
import kintai.api.KintaiAggregateRequest;
import kintai.api.KintaiRegisterApi;
import kintai.api.KintaiRegisterCheckApi;
import kintai.api.KintaiRegisterCheckRequest;
import kintai.api.KintaiRegisterRequest;
import kintai.domain.HiKintai;
import kintai.domain.company.KintaiCheckStatus;

import java.util.List;

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

                KintaiRegisterCheckApi kintaiRegisterCheckApi = new KintaiRegisterCheckApi();
                KintaiRegisterCheckRequest kintaiRegisterCheckRequest = new KintaiRegisterCheckRequest(args[1], args[2], args[3]);

                if (kintaiRegisterCheckApi.check(kintaiRegisterCheckRequest).equals(KintaiCheckStatus.OK)) {
                    KintaiRegisterApi kintaiRegisterApi = new KintaiRegisterApi();
                    KintaiRegisterRequest kintaiRegisterRequest = new KintaiRegisterRequest(args[1], args[2], args[3]);

                    kintaiRegisterApi.register(kintaiRegisterRequest);
                    System.out.println("input_ok");
                } else {
                    System.out.println("input_ng");
                }

            } else if ("total".equals(methodType)) {
                if (args.length < 2) {
                    throw new RuntimeException("引数が足りません");
                }

                KintaiAggregateApi kintaiAggregateApi = new KintaiAggregateApi();
                KintaiAggregateRequest kintaiAggregateRequest = new KintaiAggregateRequest(args[1]);

                List<HiKintai> list = kintaiAggregateApi.refer(kintaiAggregateRequest);

                int totalWork = 0;
                int totalOverWork = 0;

                for (HiKintai kintai : list) {
                    totalWork += kintai.getWorkMinutes().getValue();
                    totalOverWork += kintai.getOverWorkMinutes().getValue();
                }

                System.out.println("出勤日数: " + list.size() + "日");
                System.out.println("勤務時間: " + totalWork / 60 + "時間" + totalWork % 60 + "分");
                System.out.println("残業時間: " + totalOverWork / 60 + "時間" + totalOverWork % 60 + "分");

                System.out.println("total_ok");

            } else {
                throw new RuntimeException("methodTypeが不正です");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}