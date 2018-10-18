package api;

import datasource.Datasource;
import domain.DateVO;
import domain.TimeVO;
import service.Service;

public class Api {
    public static void main(String[] args) {
        Service sv = new Service();
        
        InputChecker inputChecker = new InputChecker();

        //コマンドライン引数が無しの場合、エラーを出力する。
        inputChecker.checkNumEmpty(args.length);

        //コマンドライン引数[0]の値に応じて、勤怠の登録or表示を行う。
        if("input".equals(args[0])) {

            //コマンドライン引数の数が足りなかったら、エラーを出力する。
            inputChecker.checkWhenInput(args.length);

            DateVO dateVO = new DateVO(args[1]);
            TimeVO timeVO = new TimeVO(args[2], args[3]);
            TimeGetter timeGetter = new TimeGetter();

            sv.registryKintai(dateVO, timeVO, timeGetter, new Datasource());

        } else if("total".equals(args[0])) {

            //コマンドライン引数の数が足りなかったら、エラーを出力する。
            inputChecker.checkWhenTotal(args.length);

            DateVO dateVO = new DateVO(args[1]);
            sv.displayMonthWorkTime(dateVO, new Datasource());

        } else {
            throw new RuntimeException("methodTypeが不正です");
        }
    }
}
