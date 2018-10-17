package api;

import datasource.RepositoryDb;
import service.ArgsVO;
import service.Service;

public class Api { //入出力を行う
    public static void main(String[] args) {

        Service sv = new Service();
        RepositoryDb repoDb = new RepositoryDb(); //Datasource

        //Application以下でプリミティブ型を排除するためVOを使う
        ArgsVO argsVO = new ArgsVO(args);

        sv.service(argsVO, repoDb);
    }
}
