package src.com.naosim.dddwork.service;

import java.util.*;

import src.com.naosim.dddwork.datasource.CsvOperat;
import src.com.naosim.dddwork.domain.*;

public class TotalService {

    public ResultTotal total(String yearMonth) {

        KintaiRepository repository = new CsvOperat();
        Map<WorkingDate, WorkTime> kintaiWorkMinutesMap = repository.getTotalWorkMinutesMapOf(yearMonth);
        Map<WorkingDate, OverTime> kintaiOverWorkMinutesMap = repository.getTotalOverWorkMinutesMapOf(yearMonth);
        SubTotal subTotal = new SubTotal();
        int totalWorkMinutes = subTotal.sumKintaiWorkMinutes(kintaiWorkMinutesMap);
        int totalOverWorkMinutes = subTotal.sumKintaiOverWorkMinutes(kintaiOverWorkMinutesMap);

        return new ResultTotal(totalWorkMinutes, totalOverWorkMinutes);

    }
}