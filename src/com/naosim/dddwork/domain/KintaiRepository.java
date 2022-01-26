package src.com.naosim.dddwork.domain;

import java.util.List;

public interface KintaiRepository {
    void write(Regulation kintai) ;
    List<String[]> read();
}