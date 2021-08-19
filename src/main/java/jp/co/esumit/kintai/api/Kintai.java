package jp.co.esumit.kintai.api;

public class Kintai {
    public static void main(String args[]) {
        try {

            Validator validator = new Validator();
            validator.isValidArgs(args);

            KintaiController controller = new KintaiController();

            switch (args[0]) {
                //**入力**
                case MethodType.INPUT:

                    controller.executeInput(args);
                    break;

                //**出力**
                case MethodType.TOTAL:

                    controller.executeTotal(args);
                    controller.consoleOut();
                    break;

                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("処理を終了します。");
        }
    }
}
