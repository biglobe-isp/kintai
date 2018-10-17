package refoctor.domain;

public class ArgsList {
    private final String[] args;

    public ArgsList(String[] args) {
        this.args = args;
    }

    public void checkLength() {
        if (args.length < 1) {
            throw new RuntimeException("引数が足りません");
        }
    }

    public MethodType getMethodType() {
        if ("input".equals(args[0])) {
            return MethodType.input;
        } else if ("total".equals(args[0])) {
            return MethodType.total;
        } else {
            throw new RuntimeException("Method Typeが不正です");
        }
    }

    public void inputCheckLength() {
        if (args.length < 4) {
            throw new RuntimeException("引数が足りません");
        }
    }

    public void totalCheckLength() {
        if (args.length < 2) {
            throw new RuntimeException("引数が足りません");
        }
    }

    public String[] getArgs() {
        return args;
    }

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
