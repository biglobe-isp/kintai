package form;

public class InputDataForm {

    public InputDataForm(String[] inputData) {
        if (inputData.length < 1) {
            throw new RuntimeException("引数が足りません");
        }

        String methodType = inputData[0];

        // ??? API層と同じif文があるのは良いの？
        // メソッドにわける
        if ("input".equals(methodType)) {
            if (inputData.length < 4) {
                throw new RuntimeException("引数が足りません");
            }
            // todo inputData[XX]も同様にチェックする
            validateStart(inputData[2]);
        } else if ("total".equals(methodType)) {
            if (inputData.length < 2) {
                throw new RuntimeException("引数が足りません");
            }
        } else {
            throw new RuntimeException("methodTypeが不正です");
        }
    }

    // クラス
    private static void validateStart(String start){
        if (start.length() != 4) {
            throw new RuntimeException("開始時刻の指定が不正です");
        }
        // todo HHは徹夜を想定して99までとする
        // todo MMは00〜59までとする
    }
}
