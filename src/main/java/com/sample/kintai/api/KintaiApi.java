package com.sample.kintai.api;

import com.sample.kintai.service.NyuryokuService;
import com.sample.kintai.service.SyukeiService;

public class KintaiApi {
    public static void main(String[] args) {
        try {

            if (args.length < 1) {
                throw new RuntimeException("引数が足りません");
            }
            String methodType = args[0];
            if ("input".equals(methodType)) {
                if(args.length < 4) {
                    throw new RuntimeException("引数が足りません");
                }
                NyuryokuService service = new NyuryokuService();
                service.nyuryoku(args);
            } else if ("total".equals(methodType)) {
                String yearMonth = args[1];
                if (args.length < 2) {
                    throw new RuntimeException("引数が足りません");
                }
                SyukeiService service = new SyukeiService();
                service.syukei(args,yearMonth);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
