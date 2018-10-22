package api;

/*
 * Domain層での計算（勤務時間・残業時間が何Minutesか計算）で使う型(int型)に変換するクラス
 * Stringで受け取り、Intで返す
 */
public class ValueAdjustVO {
    //private final String date;
    private final String start;
    private final String end;

    public ValueAdjustVO(String[] args) {
        //this.date = args[1];
        this.start = args[2];
        this.end = args[3];
    }

    //Integer.valueOf(hoge)
    public int getIntStartH() {
        return Integer.valueOf(this.start.substring(0, 2));
    }

    public int getIntStartM() {
        return Integer.valueOf(this.start.substring(2, 4));
    }

    public int getIntEndH() {
        return Integer.valueOf(this.end.substring(0, 2));
    }

    public int getIntEndM() {
        return Integer.valueOf(this.end.substring(2, 4));
    }


//    public String getValue() {
//        return date;
//    }

    public String getStart() {
        return start;
    }

//    public String getValue() {
//        return end;
//    }
}

