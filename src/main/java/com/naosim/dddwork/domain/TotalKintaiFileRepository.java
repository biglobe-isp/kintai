package com.naosim.dddwork.domain;

import java.io.IOException;
import java.util.List;

public interface TotalKintaiFileRepository {
    // TODO: メソッド名を業務に即したものに
    List<String> execute() throws IOException;
}
