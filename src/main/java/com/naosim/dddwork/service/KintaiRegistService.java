package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.kintai.regist.KintaiRegist;
import com.naosim.dddwork.domain.kintai.regist.repository.KintaiRegistRepository;
import com.naosim.dddwork.domain.kintai.regist.WorkStartAndEndTimeOfOneDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KintaiRegistService {

    @Autowired
    KintaiRegistRepository kintaiRegistRepository;

    public void registKintaiOfOneDay(WorkStartAndEndTimeOfOneDay workStartAndEndTimeOfOneDay) {
        KintaiRegist kintaiRegist = new KintaiRegist(workStartAndEndTimeOfOneDay);
        this.kintaiRegistRepository.regist(kintaiRegist.getKintaiOfOneDay());
    }
}
