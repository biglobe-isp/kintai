package jp.co.esumit.kintai.datasource;

/**
 * 定数クラス
 */
public final class AppConst {
    /**
     * method type
     */
    public static class MethodType {
        public static final String INPUT = "input";
        public static final String TOTAL = "total";
    }

    /**
     * 引数
     */
    public static class ArgsCount {
        public static final int INPUT_CNT = 4;
        public static final int TOTAL_CNT = 2;
    }

    /** 定時間[分]　**/
    public static final int fixedMinutes = 8 * 60;

    /** 出力ファイル名 **/
    public static final String FILE_NAME = "data.csv";
}
