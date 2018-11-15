package refactor.api;

import refactor.datasource.CsvWriter;
import refactor.domain.EndTime;
import refactor.domain.OverWorkMinutes;
import refactor.domain.StartTime;
import refactor.domain.WorkMinutes;
import refactor.service.AggregationService;
import refactor.service.InputService;

public class KintaiApi {
    //各サービスを呼びに行く(InputService)(service層,domain層)
    public static void main(String[] args) {
        new MethodValidation().methodTypeValidator(args);

        CsvWriter csvWriter = new CsvWriter();
        new InputService().inputWorkTimeData(args, csvWriter);

        //argsの引数をApi層がチェックしてdomain呼ぶ
        StartTime startTime = new StartTime(args[2]);
        EndTime endTime = new EndTime(args[3]);

        WorkMinutes workMinutes = new WorkMinutes();
        int workMinutes2 = workMinutes.calculateWorkMinutes(startTime, endTime);

        int overWorkMinutes = new OverWorkMinutes().calculateOverWorkMinutes(workMinutes2);

        String yearMonth = args[1];

        int totalWorkMinutes = 0;
        int totalOverWorkMinutes = 0;

        new AggregationService().calculateTotalWorkMinutesAndTotalOverWorkMinutes(
                yearMonth,
                totalWorkMinutes,
                totalOverWorkMinutes
        );
    }

}
