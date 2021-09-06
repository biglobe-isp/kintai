package jp.co.esumit.kintai;

import jp.co.esumit.kintai.api.MethodType;
import jp.co.esumit.kintai.api.Validator;
import jp.co.esumit.kintai.api.controller.InputController;
import jp.co.esumit.kintai.api.controller.TotalController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class Kintai {
    private final InputController input;
    private final TotalController total;

    public void main(String args[]) {
        try {

            Validator validator = new Validator();
            validator.isValidArgs(args);

            MethodType methodType = MethodType.findBylabel(args[0]);

            // args[0]ではなく固定のMethodTypeのenumとかで型指定する。
            switch (methodType) {
                //**入力**
                case INPUT:

                    input.execute(args);
                    break;

                //**出力**
                case TOTAL:

                    total.execute(args);
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("処理を終了します。");
        }
    }
}
