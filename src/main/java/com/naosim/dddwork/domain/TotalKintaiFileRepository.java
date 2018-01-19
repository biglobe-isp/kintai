package com.naosim.dddwork.domain;

import java.io.IOException;
import java.util.List;

public interface TotalKintaiFileRepository {
    List<String> execute() throws IOException;
}
