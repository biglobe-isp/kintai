package api;

import datasource.Datasource;
import domain.EndTimeVO;
import domain.StartTimeVO;
import domain.WorkDateVO;
import domain.WorkYearMonthVO;
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
        Service sv = new Service();

        InputChecker inputChecker = new InputChecker();

        //コマンドライン引数が無しの場合、エラーを出力する。
        inputChecker.checkNumEmpty(args.length);

        //コマンドライン引数の値に応じて、勤怠の登録or表示を行う。
        if(inputChecker.isInput(args)) {

            WorkDateVO workDateVO = new WorkDateVO(args[0]);
            StartTimeVO startTimeVO = new StartTimeVO(args[1]);
            EndTimeVO endTimeVO = new EndTimeVO(args[2]);
            TimeGetter timeGetter = new TimeGetter();

            sv.registryKintai(workDateVO, startTimeVO, endTimeVO, timeGetter, new Datasource());

        } else if(inputChecker.isOutput(args)) {

            WorkYearMonthVO workYearMonthVO = new WorkYearMonthVO(args[0]);
            sv.displayMonthWorkTime(workYearMonthVO, new Datasource());

        } else {
            throw new RuntimeException("methodTypeが不正です");
        }
    }
}
