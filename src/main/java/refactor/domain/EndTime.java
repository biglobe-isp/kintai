package refactor.domain;

//業務ロジックを書くところ
public class EndTime {
    private int endH;
    private int endM;

    public EndTime(String end) {
        this.endH = Integer.valueOf(end.substring(0, 2));
        this.endM = Integer.valueOf(end.substring(2, 4));
    }

    public int getEndH() {
        return endH;
    }

    public int getEndM() {
        return endM;
    }


}
