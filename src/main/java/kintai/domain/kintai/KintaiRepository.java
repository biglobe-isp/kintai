package kintai.domain.kintai;

import java.util.HashMap;

public interface KintaiRepository
{
    public void store(Kintai kintai);
    public HashMap<String, Kintai> KintaisOfGetsudo(Getsudo getsudo);
}