package refactor.api.fuction;

public class AttendanceFunction {

    /**
     * 引数存在チェック
     * @param args
     * @throws RuntimeException
     */
    public static void validateExsitArg(String[] args) throws RuntimeException{
        if (args.length < 1) {
            throw new RuntimeException("引数が足りません");
        }
    }

    /**
     * 登録時の引数チェック
     * @param args
     * @throws RuntimeException
     */
    public static void validateArgInInput(String[] args)throws RuntimeException{
        if (args.length < 4) {
            throw new RuntimeException("引数が足りません");
        }
    }

}
