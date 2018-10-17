package service;

import domain.DatasourceRepository;
import domain.Domain;
import domain.KintaiHyoji;
import domain.KintaiToroku;

public class Service {

    public void registryData(KintaiToroku kintaiToroku, DatasourceRepository datasourceRepository) {
        Domain dm = new Domain();
        dm.registryData(kintaiToroku, datasourceRepository);
    }

    public void displayData(KintaiHyoji kintaiHyoji, DatasourceRepository datasourceRepository) {
        Domain dm = new Domain();
        dm.displayData(kintaiHyoji, datasourceRepository);
    }
}