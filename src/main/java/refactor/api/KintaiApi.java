package refactor.api;

import refactor.datasource.CsvWriter;
import refactor.domain.Date;
import refactor.domain.EndTime;
import refactor.domain.NowTime;
import refactor.domain.StartTime;
import refactor.domain.YearMonth;
import refactor.service.AggregationService;
import refactor.service.InputService;

public class KintaiApi {
    //各サービスを呼びに行く(InputService)(service層,domain層)
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
                //argsの引数をApi層がチェックしてdomain呼ぶ
                Date date = new Date(args[1]);
                StartTime startTime = new StartTime(args[2]);
                EndTime endTime = new EndTime(args[3]);
                NowTime nowTime = new NowTime();

                CsvWriter csvWriter = new CsvWriter();
                new InputService().inputWorkTimeData(date, startTime, endTime, csvWriter, nowTime);
            } else if ("total".equals(methodType)) {
                if (args.length < 2) {
                    throw new RuntimeException("引数が足りません");
                }
                YearMonth yearMonth = new YearMonth(args[1]);

                int totalWorkMinutes = 0;
                int totalOverWorkMinutes = 0;

                new AggregationService().calculateTotalWorkMinutesAndTotalOverWorkMinutes(
                        yearMonth.getYearMonth(),
                        totalWorkMinutes,
                        totalOverWorkMinutes
                );
            } else {
                throw new RuntimeException("methodTypeが不正です");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
