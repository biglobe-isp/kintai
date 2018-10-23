package service;

import domain.japan.*;


public class DatetimeService {


    CalculateDomain calculateDomain = new CalculateDomain();

    public void input(DateDomain dd, StartDomain sd, EndDomain ed, WorkMinutesInterface wd, FileOutInterface fo) {

        calculateDomain.input(dd, sd, ed, wd, fo);

    }

    public void total(DateDomain dd, FileOutInterface fileOutDatasource) {

        calculateDomain.total(dd, fileOutDatasource);

    }

}
