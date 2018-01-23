package com.naosim.dddwork.domain;

import java.io.IOException;

public interface KintaiRegistRepository {
    // TODO: メソッド名を業務に即したものに
    void regist(OneDayKintai oneDayKintai) throws IOException;
}
