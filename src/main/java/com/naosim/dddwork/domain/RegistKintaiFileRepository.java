package com.naosim.dddwork.domain;

import java.io.IOException;

public interface RegistKintaiFileRepository {
    // TODO: メソッド名を業務に即したものに
    void execute(String registString) throws IOException;
}
