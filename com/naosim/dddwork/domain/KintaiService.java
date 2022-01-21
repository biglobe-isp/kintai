package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.valueObject.KintaiDate;
import com.naosim.dddwork.domain.valueObject.KintaiOverWorkMinutes;
import com.naosim.dddwork.domain.valueObject.KintaiWorkMinutes;

import java.util.Map;
import java.util.Set;

//ドメインサービス
public class KintaiService {
    private IKintaiRepository kintaiRepository;

    public KintaiService(IKintaiRepository kintaiRepository) {
        this.kintaiRepository = kintaiRepository;
    }

    public int sumKintaiWorkMinutes(Map<KintaiDate, KintaiWorkMinutes> kintaiWorkMinutesMap) {
        int totalWorkMinutes = 0;

        Set<KintaiDate> keySet = kintaiWorkMinutesMap.keySet();
        for (KintaiDate key : keySet) {
            totalWorkMinutes += kintaiWorkMinutesMap.get(key).getValue();
        }

        return totalWorkMinutes;
    }

    public int sumKintaiOverWorkMinutes(Map<KintaiDate, KintaiOverWorkMinutes> kintaiOverWorkMinutesMap) {
        int totalOverWorkMinutes = 0;

        Set<KintaiDate> keySet = kintaiOverWorkMinutesMap.keySet();
        for (KintaiDate key : keySet) {
            totalOverWorkMinutes += kintaiOverWorkMinutesMap.get(key).getValue();
        }

        return totalOverWorkMinutes;
    }
}






