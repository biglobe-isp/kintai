package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.KintaiRegistInput;
import com.naosim.dddwork.domain.RegistData;
import com.naosim.dddwork.domain.RegistKintaiFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KintaiRegistService {

    @Autowired
    RegistKintaiFileRepository registKintaiFileRepository;

    public void registKintaiOfOneDay(KintaiRegistInput kintaiRegistInput) throws IOException {
        RegistData registData = new RegistData(kintaiRegistInput);
        this.registKintaiFileRepository.execute(registData.getOutputData());
    }
}
