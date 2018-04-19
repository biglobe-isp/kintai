package com.naosim.dddwork.api.kintai;

import com.naosim.dddwork.service.kintai.KintaiKanriInputService;
import com.naosim.dddwork.service.kintai.KintaiKanriTotalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 勤怠管理API
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KintaiKanriApi {

    @Autowired
    private final KintaiKanriInputService kintaiKanriInputService;

    @Autowired
    private final KintaiKanriTotalService kintaiKanriTotalService;

    public void main(String[] args) throws Exception {

        KintaiKanriArgs kintaiKanriArgs = new KintaiKanriArgs(args);

        // 処理
        if (kintaiKanriArgs.getMethodTypeForm().getValueObject().isInput()) {
            kintaiKanriInputService.execute(kintaiKanriArgs.makeKintaiKanriInputServiceInput());

        } else if (kintaiKanriArgs.getMethodTypeForm().getValueObject().isTotal()) {
            kintaiKanriTotalService.execute(kintaiKanriArgs.makeKintaiKanriTotalServiceInput());
        }
    }
}
