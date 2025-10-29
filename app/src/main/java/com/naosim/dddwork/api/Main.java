package com.naosim.dddwork.api;

import com.naosim.dddwork.datasource.CsvWorkTimeDataBase;
import com.naosim.dddwork.domain.WorkDataRepository;

public class Main {
    public static void main(String[] args) {
        MethodType methodType;
        WorkDataRepository workDataRepository = new CsvWorkTimeDataBase("data.csv");
        try {
            if (args.length < 1) {
                throw new RuntimeException("引数が足りません");
            }
            String inputMethodType = args[0];
            if (inputMethodType.equals("input")) {
                methodType = MethodType.INPUT;
            } else if (inputMethodType.equals("total")) {
                methodType = MethodType.TOTAL;
            } else {
                methodType = MethodType.OTHER;
            }
            switch (methodType) {
                case INPUT:
                    if (args.length < 4) {
                        throw new RuntimeException("引数が足りません");
                    }
                    WorkTimeRegisterController workTimeRegisterController = new WorkTimeRegisterController(
                            workDataRepository);

                    String date = null;
                    String startTime = null;
                    String endTime = null;

                    for (int i = 1; i < args.length; i++) {
                        if (args[i].startsWith("-date:")) {
                            date = args[i].replace("-date:", "");
                        } else if (args[i].startsWith("-start:")) {
                            startTime = args[i].replace("-start:", "").replace("_", "");
                        } else if (args[i].startsWith("-end:")) {
                            endTime = args[i].replace("-end:", "").replace("_", "");
                        } else {
                            throw new RuntimeException("形式に則ってくださいよ〜");
                        }
                    }

                    if (date == null || startTime == null || endTime == null) {
                        throw new RuntimeException("引数が空ですよ〜");
                    }
                    workTimeRegisterController.invoke(date, startTime, endTime);
                    break;
                case TOTAL:
                    if (args.length < 2) {
                        throw new RuntimeException("引数が足りません");
                    }
                    String yearMonth = args[1];
                    MonthlySumWorkTimeCalculateController monthlySumWorkTimeCalculateController = new MonthlySumWorkTimeCalculateController(
                            workDataRepository);
                    monthlySumWorkTimeCalculateController.callTotalMonthTime(yearMonth);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
