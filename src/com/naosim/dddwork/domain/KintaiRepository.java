package src.com.naosim.dddwork.domain;

import java.util.List;

public interface KintaiRepository {
    void write(String date ,String start, String end ,int workTime, int overWorkTime) ;
    List<String[]> read();
}