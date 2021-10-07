package service;

import domain.KintaiRegisterRepositoryFile;
import org.springframework.beans.factory.annotation.Autowired;

public class KintaiCountUpService {
    @Autowired
    KintaiRegisterRepositoryFile kintaiRegisterRepositoryFile;

    public void kintaiCountUpService(String value) {

        kintaiRegisterRepositoryFile.countUp(value);
    }
}