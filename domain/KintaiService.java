package com.naosim.dddwork.domain;

import java.util.Map;
import java.util.Set;

//ドメインサービス
public class KintaiService {
    private IKintaiRepository kintaiRepository;

    public KintaiService(IKintaiRepository kintaiRepository) {
        this.kintaiRepository = kintaiRepository;
    }

    public KintaiTotalModel sumWorkTime(Map<String, Integer> totalWorkMinutesMap, Map<String, Integer> totalOverWorkMinutesMap) {

        int totalWorkMinutes = 0;
        int totalOverWorkMinutes = 0;

        Set<String> keySet = totalWorkMinutesMap.keySet();
        for (String key : keySet) {
            totalWorkMinutes += totalWorkMinutesMap.get(key);
            totalOverWorkMinutes += totalOverWorkMinutesMap.get(key);
        }

        return new KintaiTotalModel(totalWorkMinutes, totalOverWorkMinutes);
    }
}






