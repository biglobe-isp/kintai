package com.naosim.dddwork.api;

import com.naosim.dddwork.datasource.KintaiRepository;
import com.naosim.dddwork.domain.IKintaiRepository;
import com.naosim.dddwork.domain.KintaiTotalModel;
import com.naosim.dddwork.service.KintaiApplicationService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class KintaiController {
    private KintaiApplicationService kintaiService;

    public KintaiController(KintaiApplicationService kintaiService) {
        this.kintaiService = kintaiService;
    }

    public void api(String[] args) {

        try {
            if (args.length < 1) {
                throw new RuntimeException("引数が足りません");
            }

            String methodType = args[0];
            if ("input".equals(methodType)) {
                if (args.length < 3) {
                    throw new RuntimeException("引数が足りません");
                }
                Map<String, String> argsMap = this.getOptionArgs(args);

                this.register(argsMap.get("-date"), argsMap.get("-start"), argsMap.get("-end"));
            } else if ("total".equals(methodType)) {
                if (args.length < 2) {
                    throw new RuntimeException("引数が足りません");
                }
                this.printTotal(args[1]);
            } else {
                throw new RuntimeException("methodTypeが不正です");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void register(String date, String start, String end) {
        IKintaiRepository kintaiRepository = new KintaiRepository();
        KintaiApplicationService kintaiApplicationService = new KintaiApplicationService(kintaiRepository);
        kintaiApplicationService.registerKintai(date, start, end);
    }

    public void printTotal(String yearMonth) {
        KintaiTotalModel kintaiTotalModel = kintaiService.getTotalWorkTime(yearMonth);
        int totalWorkMinutes = kintaiTotalModel.getTotalWorkMinutes();
        int totalOverWorkMinutes = kintaiTotalModel.getTotalOverWorkMinutes();

        System.out.println("勤務時間: " + totalWorkMinutes / 60 + "時間" + totalWorkMinutes % 60 + "分");
        System.out.println("残業時間: " + totalOverWorkMinutes / 60 + "時間" + totalOverWorkMinutes % 60 + "分");
    }


    private Map<String, String> getOptionArgs(String[] args) {

        Map<String, String> argsMap = new HashMap<>();

        if (Arrays.asList(args).contains("v")) {
            argsMap.put("-start", "0900");
            argsMap.put("-end", "1800");
        } else if (Arrays.asList(args).contains("am")) {
            argsMap.put("-start", "0900");
        } else if (Arrays.asList(args).contains("pm")) {
            argsMap.put("-end", "1800");
        }

        for (String arg : args) {
            String[] param = arg.split(":");
            switch (param[0]) {
                case "-date":
                    argsMap.put("-date", param[1]);
                    break;
                case "-start":
                    argsMap.put("-start", param[1].replace("_", ""));
                    break;
                case "-end":
                    argsMap.put("-end", param[1].replace("_", ""));
                    break;
                case "input":
                case "v":
                case "am":
                case "pm":
                    break;
                default:
                    throw new RuntimeException("誤った引数が指定されています");
            }
        }

        return argsMap;
    }

}
