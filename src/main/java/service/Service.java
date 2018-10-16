package service;

//import datasource.RepositoryDb; //serviceからdatasourceを参照してはいけない

import domain.Domain;
import domain.IRepository;

public class Service {
    public void service(String[] args, IRepository iRepository) {
        try {
            Domain dm = new Domain();
            dm.checkArgsLength(args.length);

            String methodType = args[0];

            //TODO 分岐処理がService層で良いのか検討する
            if ("input".equals(methodType)) {
                dm.inputData(args, iRepository);
            } else if ("total".equals(methodType)) {
                //dm.outputData(args, iRepository); //TODO Datasource
            } else {
                throw new RuntimeException("methodTypeが不正です");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
