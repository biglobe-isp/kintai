package kintai.domain.shugyo_kisoku.kyukei_jikan_kisoku;

import java.util.ArrayList;

public class KyukeiJikanKisoku {
    private ArrayList<KiteiKyukeiJikan> kiteiKyukeiJikanList;

    public KyukeiJikanKisoku(ArrayList<KiteiKyukeiJikan> kiteiKyukeiJikanList) {
        this.setKiteiKyukeiJikanList(kiteiKyukeiJikanList);
    }

    public ArrayList<KiteiKyukeiJikan> getKiteiKyukeiJikanList() {
        return this.kiteiKyukeiJikanList;
    }

    private void setKiteiKyukeiJikanList(ArrayList<KiteiKyukeiJikan> kiteiKyukeiJikanList) {
        this.kiteiKyukeiJikanList = kiteiKyukeiJikanList;
    }
}
