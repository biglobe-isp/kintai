package api;

import datasource.Datasource;
import domain.japan.*;
import service.Service;

/**
 * API層のメインの処理を担うクラス。
 */
public class Api {

    /**
     * mainメソッド
     * @param args
     */
    public static void main(String[] args) {

        InputChecker inputChecker = new InputChecker();
        Service sv = new Service();

        //ユーザの要求をチェックする。
        InputChecker.UserRequest userRequest = inputChecker.checkInput(args);

        switch (userRequest) {
            case KintaiToroku:
                //コマンドライン引数が格納されている配列の順番を調整。
                ArgsChanger argsChanger = new ArgsChanger();
                args = argsChanger.changeArgs(args);

                //Valueオブジェクトを生成。
                WorkDateVO workDateVO = new WorkDateVO(args[0]);
                StartTime startTime = new StartTime(args[1]);
                EndTime endTime = new EndTime(args[2]);
                WorkTimeMinuteVO workTimeMinuteVO = new WorkTimeMinuteVO(startTime, endTime);
                OverWorkTimeMinuteVO overWorkTimeMinuteVO = new OverWorkTimeMinuteVO(workTimeMinuteVO);

                //勤怠登録を行う。
                sv.registryKintai(workDateVO, startTime, endTime, workTimeMinuteVO, overWorkTimeMinuteVO,
                                    new TimeGetter(), new Datasource());
                break;

            case KintaiHyoji:
                //Valueオブジェクトを生成。
                WorkYearMonthVO workYearMonthVO = new WorkYearMonthVO(args[0]);

                //勤怠表示を行う。
                sv.displayMonthWorkTime(workYearMonthVO, new Datasource());
                break;

             default:
                 throw new RuntimeException("methodTypeが不正です");
        }
    }
}