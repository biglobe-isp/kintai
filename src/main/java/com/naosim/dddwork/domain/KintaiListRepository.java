package com.naosim.dddwork.domain;

import java.io.IOException;

public interface KintaiListRepository {
    KintaiLines get() throws IOException;
}
