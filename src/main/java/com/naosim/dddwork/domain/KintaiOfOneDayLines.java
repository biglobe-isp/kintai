package com.naosim.dddwork.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class KintaiOfOneDayLines {

    private List<KintaiOfOneDayLine> kintaiOfOneDayLineList;

    public KintaiOfOneDayLines(List<KintaiOfOneDayLine> kintaiOfOneDayLineList) {
        this.kintaiOfOneDayLineList = kintaiOfOneDayLineList;
    }

    public KintaiOfOneDayLines add(KintaiOfOneDayLine kintaiOfOneDayString) {
        List<KintaiOfOneDayLine> tmpKintaiOfOneDayStrings = new ArrayList<>(this.kintaiOfOneDayLineList);
        tmpKintaiOfOneDayStrings.add(kintaiOfOneDayString);
        return new KintaiOfOneDayLines(tmpKintaiOfOneDayStrings);
    }

    public Iterator<KintaiOfOneDayLine> getIterator() {
        return this.kintaiOfOneDayLineList.iterator();
    }
}
