//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.naosim.dddwork.api.kintai;

import com.naosim.dddwork.service.kintai.KintaiKanriInputService;
import com.naosim.dddwork.service.kintai.KintaiKanriTotalService;
import java.beans.ConstructorProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KintaiKanriApi {
    @Autowired
    private final KintaiKanriInputService kintaiKanriInputService;
    @Autowired
    private final KintaiKanriTotalService kintaiKanriTotalService;

    public void main(String[] args) throws Exception {
        KintaiKanriArgs kintaiKanriArgs = new KintaiKanriArgs(args);
        if (kintaiKanriArgs.getMethodTypeForm().getValueObject().isInput()) {
            this.kintaiKanriInputService.execute(kintaiKanriArgs.makeKintaiKanriInputServiceInput());
        } else if (kintaiKanriArgs.getMethodTypeForm().getValueObject().isTotal()) {
            this.kintaiKanriTotalService.execute(kintaiKanriArgs.makeKintaiKanriTotalServiceInput());
        }

    }

    @ConstructorProperties({"kintaiKanriInputService", "kintaiKanriTotalService"})
    @Autowired
    public KintaiKanriApi(KintaiKanriInputService kintaiKanriInputService, KintaiKanriTotalService kintaiKanriTotalService) {
        this.kintaiKanriInputService = kintaiKanriInputService;
        this.kintaiKanriTotalService = kintaiKanriTotalService;
    }
}
