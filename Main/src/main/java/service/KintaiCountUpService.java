package service;

import api.KintaiCountUpRequest;
import domain.KintaiRegisterRepositoryFile;
import domain.TotallyMonth;
import org.springframework.beans.factory.annotation.Autowired;

public class KintaiCountUpService {
    @Autowired
    KintaiRegisterRepositoryFile kintaiRegisterRepositoryFile;

    public void kintaiCountUpService(TotallyMonth totallyMonth) {

        kintaiRegisterRepositoryFile.countUp(totallyMonth.getValue());
    }
}