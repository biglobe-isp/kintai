package com.naosim.dddwork.domain.kintai.totalprint;

import com.naosim.dddwork.domain.kintai.KintaiOfOneDay;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class KintaiOfOneDays {

    private List<KintaiOfOneDay> kintaiOfOneDayList;

    public KintaiOfOneDays(List<KintaiOfOneDay> kintaiOfOneDayLineList) {
        this.kintaiOfOneDayList = kintaiOfOneDayLineList;
    }

    public KintaiOfOneDays add(KintaiOfOneDay kintaiOfOneDay) {
        List<KintaiOfOneDay> tmpKintaiOfOneDayList = new ArrayList<>(this.kintaiOfOneDayList);
        tmpKintaiOfOneDayList.add(kintaiOfOneDay);
        return new KintaiOfOneDays(tmpKintaiOfOneDayList);
    }

    public Iterator<KintaiOfOneDay> getIterator() {
        return this.kintaiOfOneDayList.iterator();
    }
}
