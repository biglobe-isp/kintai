package com.naosim.dddwork.domain;

import java.time.LocalDate;
import java.util.List;

public interface IKintaiDataRepository {
    void save(KintaiData kintai);

    List<KintaiData> findKintaiDataByMonth(LocalDate date);
}
