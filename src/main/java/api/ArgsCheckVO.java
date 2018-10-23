package api;

public class ArgsCheckVO {
    private final String[] args;

    public ArgsCheckVO(String[] args) {
        this.args = args;
    }

    public void checkArgsLength() {
        if (args.length < 1) {
            throw new RuntimeException("引数が足りません");
        }
    }

    public MethodType getMethodType() { //enum
        if ("input".equals(args[0])) {
            return MethodType.input;

        } else if ("total".equals(args[0])) {
            return MethodType.total;

        } else {
            throw new RuntimeException("methodTypeが不正です");
        }
    }

    /*
     * 引数を前方一致で判定。
     * -date* ,-start* ,-endが共に与えられた時のみtrueを返す
     *
     */
    public boolean isArgsStartWith(ArgsCheckVO args) throws Exception {
        boolean isArgs = false;
        boolean isDate = false;
        boolean isStart = false;
        boolean isEnd = false;

        for (String value : args.getValue()) { //TODO Streamを使う書き方を試すべき
            if (value.startsWith("-" + ArgsStartWith.date + ":")) {
                isDate = true;
                //DateVO date = new DateVO(value.substring(5, 12));
            } else if (value.startsWith("-" + ArgsStartWith.start + ":")) {
                isStart = true;
                //argsVO.getValue()[1] = value.substring(6, 9);
                //StartVO start = new StartVO(value.substring(6, 9));
            } else if (value.startsWith("-" + ArgsStartWith.end + ":")) {
                isEnd = true;
                //argsVO.getValue()[2] = value.substring(4, 7);
                //EndVO end = new EndVO(value.substring(4, 7));
            } else {
                //Errorの際にどこがエラーかを出力できた方がより良い。
                throw new Exception("inputの後には、-date・-start・-endを全て入力して下さい。");
            }
        }
        /* Streamにするならこんな書き方
         * filter　はtrueなら残す、
         * Stream.of(args).filter(v -> v.startWith("-date")) || v.startWith("-start")) ||v.startWith("-end"))
         * count == 3
         */
        if (isDate && isStart && isEnd) {
            isArgs = true;
        }
        return isArgs;
    }


    public String[] getValue() {
        return args;
    }

    public void inputCheckArgsLength() {
        if (args.length < 4) {
            throw new RuntimeException("引数が足りません");
        }
    }

    public void totalCheckArgsLength() {
        if (args.length < 2) {
            throw new RuntimeException("引数が足りません");
        }
    }
}
