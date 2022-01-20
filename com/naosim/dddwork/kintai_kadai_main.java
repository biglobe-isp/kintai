package com.naosim.dddwork;

import com.naosim.dddwork.api.KintaiController;
import com.naosim.dddwork.datasource.KintaiRepository;
import com.naosim.dddwork.domain.IKintaiRepository;
import com.naosim.dddwork.service.KintaiApplicationService;

public class kintai_kadai_main {
    public static void main(String[] args) {

        IKintaiRepository kintaiRepository = new KintaiRepository();
        KintaiApplicationService kintaiApplicationService = new KintaiApplicationService(kintaiRepository);
        KintaiController kintaiController = new KintaiController(kintaiApplicationService);
        kintaiController.api(args);
    }
}

