package refactor.domain;

//業務ロジックを書くところ
public class StartTime {

    private int startH;

    private int startM;

    public StartTime(String  start) {
        this.startH = Integer.valueOf(start.substring(0, 2));
        this.startM = Integer.valueOf(start.substring(2, 4));
    }

    public int getStartH() {
        return startH;
    }

    public int getStartM() {
        return startM;
    }
}
