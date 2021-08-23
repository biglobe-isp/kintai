package jp.co.esumit.kintai.api;

import jp.co.esumit.kintai.api.controller.InputController;
import jp.co.esumit.kintai.api.controller.TotalController;

public class Kintai {
    public static void main(String args[]) {
        try {

            Validator validator = new Validator();
            validator.isValidArgs(args);


            switch (args[0]) {
                //**入力**
                case MethodType.INPUT:

                    InputController input = new InputController();
                    input.execute(args);
                    break;

                //**出力**
                case MethodType.TOTAL:

                    TotalController total = new TotalController();
                    total.execute(args);
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
