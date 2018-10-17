package api;

import datasource.Datasource;
import domain.KintaiHyoji;
import domain.KintaiToroku;
import service.Service;

public class Api {
    public static void main(String[] args) {
        Service sv = new Service();

        //引数が0だったらエラー出力
        if(args.length < 1) {
            throw new RuntimeException("引数が足りません");
        }

        //コマンドライン引数[0]の値に応じて、勤怠の登録or表示を行う。
        if("input".equals(args[0])) {
            if (args.length < 4) {
                throw new RuntimeException("引数が足りません");
            }
            sv.registryData(new KintaiToroku(args[1], args[2], args[3]), new Datasource());

        } else if("total".equals(args[0])) {
            if(args.length < 2) {
                throw new RuntimeException("引数が足りません");
            }
            sv.displayData(new KintaiHyoji(args[1], new Datasource()), new Datasource());

        } else {
            throw new RuntimeException("methodTypeが不正です");
        }
    }
}
