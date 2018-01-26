package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.kintai.register.KintaiRegister;
import com.naosim.dddwork.domain.kintai.register.WorkStartAndEndTimeOfOneDay;
import com.naosim.dddwork.domain.kintai.register.repository.KintaiRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KintaiRegisterService {

    @Autowired
    KintaiRegisterRepository kintaiRegisterRepository;

    public void registerKintaiOfOneDay(WorkStartAndEndTimeOfOneDay workStartAndEndTimeOfOneDay) {
        KintaiRegister kintaiRegister = new KintaiRegister(workStartAndEndTimeOfOneDay);
        this.kintaiRegisterRepository.register(kintaiRegister.getKintaiOfOneDay());
    }
}
