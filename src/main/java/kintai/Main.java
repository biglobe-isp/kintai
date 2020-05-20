package kintai;

public class Main {
    public static void main(String[] args) {
        init();
        executeAPI(args);
    }

    private static void init() {
        ContainerInitializer containerInitializer = new KadaiContainerInitializer();
        containerInitializer.init();
    }

    private static void executeAPI(String[] args) {
        switch (methodType(args)) {
            case "input":
                Container.getNyuryokuAPI().handle(args);
                break;
            case "total":
                Container.getShukeiAPI().handle(args);
                break;
            default:
                throw new RuntimeException("methodTypeが不正です");
        }
    }

    private static String methodType(String[] args) {
        if(args.length < 1) {
            throw new RuntimeException("引数が足りません");
        }

        return args[0]; 
    }
}