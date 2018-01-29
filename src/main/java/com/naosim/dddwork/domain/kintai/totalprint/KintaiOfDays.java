package com.naosim.dddwork.domain.kintai.totalprint;

import com.naosim.dddwork.domain.kintai.KintaiOfOneDay;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class KintaiOfDays {

    @Getter
    private List<KintaiOfOneDay> kintaiOfOneDayList;

    public KintaiOfDays(List<KintaiOfOneDay> kintaiOfOneDayLineList) {
        this.kintaiOfOneDayList = kintaiOfOneDayLineList;
    }

    public KintaiOfDays add(KintaiOfOneDay kintaiOfOneDay) {
        List<KintaiOfOneDay> tmpKintaiOfOneDayList = new ArrayList<>(this.kintaiOfOneDayList);
        tmpKintaiOfOneDayList.add(kintaiOfOneDay);
        return new KintaiOfDays(tmpKintaiOfOneDayList);
    }

    public Iterator<KintaiOfOneDay> getIterator() {
        return this.kintaiOfOneDayList.iterator();
    }

}
