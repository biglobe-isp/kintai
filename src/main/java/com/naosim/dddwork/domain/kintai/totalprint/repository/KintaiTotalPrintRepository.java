package com.naosim.dddwork.domain.kintai.totalprint.repository;

import com.naosim.dddwork.domain.kintai.totalprint.KintaiTotal;

public interface KintaiTotalPrintRepository {
    void print(KintaiTotal kintaiTotal);
}
