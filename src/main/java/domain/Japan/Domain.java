package domain.Japan;

public class Domain {

    public void input(DateVO date, StartVO start, EndVO end, ICalcWorkTimeVO iWorkTime, INowRepository iNowRepo, IRepository iRepo) {
        iRepo.writeData(date, start, end, iWorkTime, iNowRepo);
    }


    public void total(DateVO date, IRepository iRepo) {
        iRepo.readData(date);
    }


}

