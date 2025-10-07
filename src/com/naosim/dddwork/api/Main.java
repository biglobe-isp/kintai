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
                    workTimeRegisterController.invoke(args[1], args[2], args[3]);
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
