package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.InputKintai;
import com.naosim.dddwork.domain.RegistData;
import com.naosim.dddwork.domain.RegistKintaiFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KintaiRegistService {

    @Autowired
    RegistKintaiFileRepository registKintaiFileRepository;

    public void registKintaiOfOneDay(InputKintai inputKintai) throws IOException {
        RegistData registData = new RegistData(inputKintai);
        this.registKintaiFileRepository.execute(registData.getOutputData());
    }
}
