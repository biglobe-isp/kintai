//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.naosim.dddwork.domain.duty;

import java.util.ArrayList;

public interface WorkingDataRepository {
    void write(WorkingData var1) throws Exception;

    ArrayList<WorkingData> read() throws Exception;

    WorkingSummaryData summary(ArrayList<WorkingData> var1);
}
