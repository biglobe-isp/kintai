package service;

import datasource.InputCsvRepositoryImpl;
import domain.*;

public class InputService {
    public InputService(
            WorkDate workDate,
            StartTime startTime,
            EndTime endTime,
            InputCsvRepositoryImpl inputCsvRepository
    ) {
        // CSV出力
        // 引数を１つずつ渡すのはではなく、PHPでやってるみたいにEntityを作成する
        // Entityはドメイン層におく
        inputCsvRepository.InputCsvAdd(workDate, startTime, endTime);
    }
}
