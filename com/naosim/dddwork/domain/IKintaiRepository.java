package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.entity.Kintai;
import com.naosim.dddwork.domain.valueObject.KintaiDate;
import com.naosim.dddwork.domain.valueObject.KintaiOverWorkMinutes;
import com.naosim.dddwork.domain.valueObject.KintaiWorkMinutes;

import java.util.Map;

public interface IKintaiRepository {
    void save(Kintai kintai);
//    KintaiMapModel getTotalWorkTimeMapsOf(String yearMonth);
    Map<KintaiDate, KintaiWorkMinutes> getTotalWorkMinutesMapOf(String yearMonth);
    Map<KintaiDate, KintaiOverWorkMinutes> getTotalOverWorkMinutesMapOf(String yearMonth);
}