package service;

//import datasource.RepositoryDb; //serviceからdatasourceを参照してはいけない

import domain.*;

public class Service {

    public void input(DateVO date, StartVO start, EndVO end, CalcWorkTimeVO workTime, INowRepository iNowRepo, IRepository iRepo) {
        Domain dm = new Domain();
        dm.input(date, start, end, workTime, iNowRepo, iRepo);
    }

    public void total(IRepository iRepo) {
        Domain dm = new Domain();
        dm.total(iRepo);
    }
}
