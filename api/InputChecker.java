package api;

/**
 * インプットした値をチェックするクラス。
 */
public class InputChecker {

    /**
     * コマンドライン引数の数が0個の場合、エラーを出力するメソッド。
     * @param num コマンドライン引数の個数
     */
    public void checkNumEmpty(int num) {
        if (num < 1) {
            throw new RuntimeException("引数が足りません");
        }
    }

    /**
     * コマンドライン引数の数が勤務登録に必要な数を満たしていない場合、エラーを出力するメソッド。
     * @param num コマンドライン引数の個数
     */
    public void checkWhenInput(int num) {
        if (num < 3) {
            throw new RuntimeException("引数が足りません");
        }
    }

    /**
     * ユーザの要求が「勤怠登録」であるかを判定するメソッド。
     * @param args コマンドライン引数の個数
     * @return
     *          true 勤怠登録である
     *          false　勤怠登録でない
     */
    public boolean isInput(String[] args) {

        //ユーザの要求が「労働時間表示」の可能性がある場合
        if (args.length == 1) {
            return false;
        }

        //コマンドライン引数の数が足りなかったら、エラーを出力する。
        checkWhenInput(args.length);

        boolean result = false;

        if (args[0].startsWith("-date:") &&
            args[1].startsWith("-start:") &&
            args[2].startsWith("-end:")) {

            result = true;

        } else {
            result = false;
        }
        return result;
    }

    /**
     * ユーザの要求が「労働時間出力」であるかを判定するメソッド。
     * @param args コマンドライン引数の個数
     * @return
     *          true 労働時間出力である
     *          false 労働時間出力でない
     */
    public boolean isOutput(String[] args) {

        boolean result = false;

        if (args[0].startsWith("-date:")) {
            result = true;

        } else {
            result = false;
        }
        return result;
    }
}
