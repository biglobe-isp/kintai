package com.naosim.dddwork.kintai.api.request.protocol;

import com.naosim.dddwork.kintai.api.request.RequestOperands;


/**
 * リクエストプロセッサー規定
 */
public interface RequestProcessor {

    void execute(RequestOperands operands);
}
