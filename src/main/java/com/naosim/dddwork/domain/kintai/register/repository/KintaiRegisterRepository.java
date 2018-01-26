package com.naosim.dddwork.domain.kintai.register.repository;

import com.naosim.dddwork.domain.kintai.KintaiOfOneDay;

public interface KintaiRegisterRepository {
    void register(KintaiOfOneDay kintaiOfOneDay);
}
