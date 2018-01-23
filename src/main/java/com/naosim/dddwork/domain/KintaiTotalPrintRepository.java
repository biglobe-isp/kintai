package com.naosim.dddwork.domain;

import java.io.IOException;
import java.util.List;

public interface KintaiTotalPrintRepository {
    // TODO: メソッド名を業務に即したものに
    List<String> execute() throws IOException;
}
