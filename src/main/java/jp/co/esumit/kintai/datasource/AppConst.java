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

    /** 登録時刻のパターン **/
    public static final String PATTERN_REGISTERED_TIME = "yyyy/MM/dd HH:mm:ss";

    /** 定時間[分]　**/
    public static final int FIXED_MINUTES = 8 * 60;

    /** 出力ファイル名 **/
    public static final String FILE_NAME = "data.csv";

    /**
     * input時のPrefix
     */
    public static class Prefix {
        public static final String DATE = "-date:";
        public static final String START = "-start:";
        public static final String END = "-end:";
    }

    /** 休憩時間 **/
    public static final int[][] BREAK_TIMES = {{12,13},{15,16},{18,19},{21,22}};
}
