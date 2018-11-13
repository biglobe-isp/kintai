package form;

public class InputDataForm {

    public InputDataForm(String[] inputData) {
        if (inputData.length < 1) {
            throw new RuntimeException("引数が足りません");
        }

        String methodType = inputData[0];
        if ("input".equals(methodType)) {
            if (inputData.length < 4) {
                throw new RuntimeException("引数が足りません");
            }
        } else if ("total".equals(methodType)) {
            if (inputData.length < 2) {
                throw new RuntimeException("引数が足りません");
            }
        } else {
            throw new RuntimeException("methodTypeが不正です");
        }
    }
}
