package api;

import datasource.InputCsvRepositoryImpl;
import datasource.TotalCsvRepositoryImpl;
import domain.EndTime;
import domain.StartTime;
import domain.WorkDate;
import form.InputDataForm;
import service.InputService;
import service.TotalService;

public class KintaiApi {

    public static void main(String[] inputData) {
        // APIからドメインを呼ぶのはOK
        // サービス層を飛ばして呼ぶのもOK
        // 循環的複雑度を調べておく
        // DateAndEndAndStart みたいに3つまとめる　クラス名が分からない場合　やっていくうちにリファクタしよう
        // 1つのクラスにつきインスタンス変数は2つまで
        // getterを使うとき、domain層から使うときは悪い設計 API層やデータソース層から使うときはしょうがない
        // イミュータブル インスタンス変数を上書きせずに新しいオブジェクトを作って返却する int x, int y new Pos(x+1, y)
        // 時間じゃなくて時刻
        try {
            //入力チェック
            new InputDataForm(inputData);

            String methodType = inputData[0];
            if ("input".equals(methodType)) {
                WorkDate workDate = new WorkDate(inputData[1]);
                StartTime startTime = new StartTime(inputData[2]);
                EndTime endTime = new EndTime(inputData[3]);

                InputCsvRepositoryImpl inputCsvRepository = new InputCsvRepositoryImpl();
                new InputService(workDate, startTime, endTime, inputCsvRepository);
            } else if ("total".equals(methodType)) {
                TotalCsvRepositoryImpl totalCsvRepository = new TotalCsvRepositoryImpl();
                new TotalService(inputData, totalCsvRepository);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
