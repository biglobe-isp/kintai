package jp.co.biglobe.isp.kintai.api;

public class Main {
    public static void main(String[] args) {
        try {
            if (args.length < 1) {
                throw new RuntimeException("引数が足りません");
            }
            switch (MethodType.of(args[0])) {
                // case INPUT ->
                // case TOTAL ->
                default -> throw new RuntimeException("引数のメソッドタイプは定義されていません。");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
