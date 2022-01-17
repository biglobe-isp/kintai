package com.naosim.dddwork.api;

import com.naosim.dddwork.datasource.KintaiRepository;
import com.naosim.dddwork.domain.IKintaiRepository;
import com.naosim.dddwork.domain.KintaiTotalModel;
import com.naosim.dddwork.service.KintaiApplicationService;

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
                if (args.length < 4) {
                    throw new RuntimeException("引数が足りません");
                }
                this.register(args[1], args[2], args[3]);
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


}
