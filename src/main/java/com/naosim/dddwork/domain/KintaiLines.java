package com.naosim.dddwork.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class KintaiLines {

    private List<String> kintaiLines;

    public KintaiLines(List<String> kintaiLines) {
        this.kintaiLines = kintaiLines;
    }

    public KintaiLines add(String line) {
        List<String> tmpKintaiLines = new ArrayList<>(this.kintaiLines);
        tmpKintaiLines.add(line);
        return new KintaiLines(tmpKintaiLines);
    }

    public Iterator<String> getIterator() {
        return this.kintaiLines.iterator();
    }
}
