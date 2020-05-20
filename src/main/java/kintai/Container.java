package kintai;

import kintai.api.nyuryoku.NyuryokuAPI;
import kintai.api.shukei.ShukeiAPI;

public class Container {
    private static NyuryokuAPI inputAPI;
    private static ShukeiAPI shukeiAPI;

    public static NyuryokuAPI getNyuryokuAPI() {
        return inputAPI;
    }

    public static ShukeiAPI getShukeiAPI() {
        return shukeiAPI;
    }

    protected static void registerNyuryokuAPI(NyuryokuAPI i) {
        inputAPI = i;
    }

    protected static void registerShukeiAPI(ShukeiAPI s) {
        shukeiAPI = s;
    }
}
