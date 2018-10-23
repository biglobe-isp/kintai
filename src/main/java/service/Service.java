package service;
//import datasource.RepositoryDb; //serviceからdatasourceを参照してはいけない

import domain.Japan.*;

public class Service {

    public void input(DateVO date, StartVO start, EndVO end, ICalcWorkTimeVO iWorkTime, INowRepository iNowRepo, IRepository iRepo) {
        Domain dm = new Domain();
        dm.input(date, start, end, iWorkTime, iNowRepo, iRepo);
    }

    public void total(DateVO date, IRepository iRepo) {
        Domain dm = new Domain();
        dm.total(date, iRepo);
    }
}
