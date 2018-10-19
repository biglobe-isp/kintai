package service;

import domain.*;
import domain.ValueForm.WorkMAndOverM;


public class DatetimeService {


    CalculateDomain calculateDomain = new CalculateDomain();

    public void input(DateDomain dd, StartDomain sd, EndDomain ed, WorkMAndOverM wd, FileOutInterface fo) {

        calculateDomain.input(dd, sd, ed, wd, fo);

    }

    public void total(DateDomain dd, FileOutInterface fileOutDatasource) {

        calculateDomain.total(dd, fileOutDatasource);

    }

}
