package com.naosim.dddwork.domain;

import java.util.Map;

public interface IKintaiRepository {
    void save(Kintai kintai);
//    KintaiMapModel getTotalWorkTimeMapsOf(String yearMonth);
    Map<KintaiDate, KintaiWorkMinutes> getTotalWorkMinutesMapOf(String yearMonth);
    Map<KintaiDate, KintaiOverWorkMinutes> getTotalOverWorkMinutesMapOf(String yearMonth);
}