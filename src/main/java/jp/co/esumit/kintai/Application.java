package jp.co.esumit.kintai;


import jp.co.esumit.kintai.api.KintaiControllerApi;
import jp.co.esumit.kintai.api.validator.ArgsValidator;
import jp.co.esumit.kintai.datasource.AppConst;

public class Application {

    public static void main(String[] args) {

        try{

            ArgsValidator validator = new ArgsValidator();
            validator.isValidArgs(args);

            KintaiControllerApi api = new KintaiControllerApi();

            switch(args[0]){
                //**入力**
                case AppConst.MethodType.INPUT:

                    api.executeInput(args);
                    break;

                //**出力**
                case AppConst.MethodType.TOTAL:

                    api.executeTotal(args);
                    break;

                default :
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("処理を終了します。");
        }

    }
}
