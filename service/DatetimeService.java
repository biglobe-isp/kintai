package service;

import domain.*;
import domain.WorkMinutesDomain;


public class DatetimeService {


    CalculateDomain calculateDomain = new CalculateDomain();

    public void input(DateDomain dd, StartDomain sd, EndDomain ed, WorkMinutesDomain wd, FileOutInterface fo) {

        calculateDomain.input(dd, sd, ed, wd, fo);

    }

    public void total(DateDomain dd, FileOutInterface fileOutDatasource) {

        calculateDomain.total(dd, fileOutDatasource);

    }

}
