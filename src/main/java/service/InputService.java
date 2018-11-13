package service;

import datasource.InputCsvRepositoryImpl;
import domain.OverWorkMinutesDomain;
import domain.WorkMinutesDomain;

public class InputService {
    public InputService(String[] inputData, InputCsvRepositoryImpl inputCsvRepository) {
        // 変数はfinalにしよう・・・そうするとsetterは使えないことになるので

        // inputDataはバリューオブジェクトにする
        // newはinputCsvRepository
        WorkMinutesDomain workMinutesDomain = new WorkMinutesDomain(inputData[2], inputData[3]);
        OverWorkMinutesDomain overWorkMinutesDomain = new OverWorkMinutesDomain(workMinutesDomain);

        // CSV出力
        // 引数を１つずつ渡すのはではなく、PHPでやってるみたいにEntityを作成する
        // Entittyはドメイン層におく
        inputCsvRepository.InputCsvAdd(inputData, workMinutesDomain.workMinutes, overWorkMinutesDomain.overWorkMinutes);
    }
}
