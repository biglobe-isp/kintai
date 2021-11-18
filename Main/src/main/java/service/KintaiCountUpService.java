package service;

import datasource.KintaiRegisterRepositoryFileImpl;
import domain.KintaiRegisterRepositoryFile;
import domain.TotallyMonth;

public class KintaiCountUpService {

    public void kintaiCountUpService(TotallyMonth totallyMonth) {

        KintaiRegisterRepositoryFile kintaiRegisterRepositoryFile = new KintaiRegisterRepositoryFileImpl();
        kintaiRegisterRepositoryFile.countUp(totallyMonth);
    }
}