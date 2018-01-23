package com.naosim.dddwork.domain;

import java.io.IOException;
import java.util.List;

public interface KintaiListRepository {
    KintaiLines get() throws IOException;
}
