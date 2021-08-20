package kintai.domain.shugyo_kisoku;

import kintai.domain.kintai.Kinmubi;

public interface ShugyoKisokuRepository {
    public ShugyoKisoku shugyoKisokuOfKinmubi(Kinmubi kinmubi);
    
    public void store(ShugyoKisoku shugyoKisoku);
}