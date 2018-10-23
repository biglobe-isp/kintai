package api;

/**
 * インプットした値をチェックするクラス。
 */
public class InputChecker {

    public enum UserRequest {
        KintaiToroku,
        KintaiHyoji,
        Error
    }

    /**
     * インプット値を判定するメソッド。
     * @param args コマンドライン引数の個数
     * @return userRequest ユーザの要求
     */
    public UserRequest checkInput(String[] args) {

        //コマンドライン引数が空の場合
        if (args.length < 1) {
            throw new RuntimeException("引数が足りません");
        }

        //ユーザの要求が勤怠表示の場合
        if(args[0].startsWith("-date:") && args.length == 1) {

            return UserRequest.KintaiHyoji;

        //ユーザの要求が勤怠登録の場合
        } else if (args.length == 3) {

            for (int i = 0; i < args.length; i++) {
                if (args[i].startsWith("-date:") ||
                    args[i].startsWith("-start:") ||
                    args[i].startsWith("-end:")) {

                } else {
                    break;
                }

                if(i == args.length - 1) {
                    return UserRequest.KintaiToroku;
                }

            }
        }

        if (args.length < 3) {
            throw new RuntimeException("引数が足りません");
        }

        return UserRequest.Error;
    }
}