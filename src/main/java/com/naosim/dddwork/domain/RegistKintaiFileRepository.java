package com.naosim.dddwork.domain;

import java.io.IOException;

public interface RegistKintaiFileRepository {
    void execute(String registString) throws IOException;
}
