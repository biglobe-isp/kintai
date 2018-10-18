package api;

public class InputChecker {

    public void checkNumEmpty(int num) {
        if (num < 1) {
            throw new RuntimeException("引数が足りません");
        }
    }

    public void checkWhenInput(int num) {
        if (num < 4) {
            throw new RuntimeException("引数が足りません");
        }
    }

    public void checkWhenTotal(int num) {
        if (num < 2) {
            throw new RuntimeException("引数が足りません");
        }
    }

}
