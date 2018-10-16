package service;

import datasource.Datasource;
import domain.Domain;

public class Service {

    public void service(String[] args, Datasource ds) {

        try {
            Domain dm = new Domain();

            dm.checkArgsLength(args.length);

            String methodType = args[0];

            //コマンドライン引数[0]の値に応じて、勤怠の登録or表示を行う。
            if("input".equals(methodType)) {
                dm.registryData(args.length, args[1], args[2], args[3], ds);

            } else if("total".equals(methodType)) {
                dm.displayData(args.length, args[1], ds);

            } else {
                throw new RuntimeException("methodTypeが不正です");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
