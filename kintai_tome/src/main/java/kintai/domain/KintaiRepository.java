package kintai.domain;

import java.io.IOException;

public interface KintaiRepository {

    void register(HiKintai hiKintai) throws IOException;
}
