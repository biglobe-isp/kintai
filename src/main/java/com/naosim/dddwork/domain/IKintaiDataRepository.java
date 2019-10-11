package com.naosim.dddwork.domain;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public interface IKintaiDataRepository {
    void save(KintaiData kintai);


    List<KintaiData> findKintaiDataByMonth(YearMonth date);
}
