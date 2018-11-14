package refactor.api.fuction;

import refactor.domain.dto.Item.ArgsItem;

public class AttendanceValidater {

    /**
     * 引数存在チェック
     * @param args
     * @throws RuntimeException
     */
    public static void validateExsitArg(ArgsItem args) throws RuntimeException{
        if (args.getArgs().length < 1) {
            throw new RuntimeException("引数が足りません");
        }
    }

    /**
     * 登録時の引数チェック
     * @param args
     * @throws RuntimeException
     */
    public static void validateArgInInput(ArgsItem args)throws RuntimeException{
        if (args.getArgs().length < 4) {
            throw new RuntimeException("引数が足りません");
        }
    }

    /**
     * 表示時の引数チェック
     * @param args
     * @throws RuntimeException
     */
    public static void validateArgTotal(ArgsItem args)throws RuntimeException{
        if(args.getArgs().length < 2) {
            throw new RuntimeException("引数が足りません");
        }
    }
}
