package api;

public class ArgsAndCheckVO {
    private final String[] args;

    public ArgsAndCheckVO(String[] args) {
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

//    public String[] getArgs() {
//        return args;
//    }

    public String getDate() {
        return args[1];
    }

    public String getStart() {
        return args[2];
    }

    public String getEnd() {
        return args[3];
    }
}
