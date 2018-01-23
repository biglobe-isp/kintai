package com.naosim.dddwork.domain;

import java.io.IOException;

public interface KintaiRegistRepository {
    void regist(OneDayKintai oneDayKintai) throws IOException;
}
