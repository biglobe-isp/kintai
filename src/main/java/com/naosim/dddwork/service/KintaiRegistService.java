package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.KintaiRegist;
import com.naosim.dddwork.domain.KintaiRegistInput;
import com.naosim.dddwork.domain.KintaiRegistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KintaiRegistService {

    @Autowired
    KintaiRegistRepository kintaiRegistRepository;

    public void registKintaiOfOneDay(KintaiRegistInput kintaiRegistInput) throws IOException {
        KintaiRegist kintaiRegist = new KintaiRegist(kintaiRegistInput);
        this.kintaiRegistRepository.regist(kintaiRegist.getKintaiOfOneDay());
    }
}
