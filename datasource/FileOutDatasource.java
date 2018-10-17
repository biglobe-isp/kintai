package datasource;

import domain.*;

public class FileOutDatasource implements FileOutInterface {

    public void kintaiOutPut(DateDomain dd, StartDomain sd, EndDomain ed, WorkMinutesDomain wd) {
        FileOutInterface.super.kintaiOutPut(dd, sd, ed, wd);
    }

    public void totalOutPut(DateDomain dd) {
        FileOutInterface.super.totalOutPut(dd);
    }

}
