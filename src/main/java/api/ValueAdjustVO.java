package api;

/*
 * Domain層での計算（勤務時間・残業時間が何Minutesか計算）で使う型(int型)に変換するクラス
 * Stringで受け取り、Intで返す
 */
public class ValueAdjustVO {
    private String date;
    private String start;
    private String end;
//TODO finalにできていない（dateなどが複数回引数で与えられた場合があるため）
//    private final String date;
//    private final String start;
//    private final String end;

    public ValueAdjustVO(ArgsCheckVO argsVO) {
        //TODO ArgsCheckVOのisArgsStartWith()内と同じなので修正する
        for (String value : argsVO.getValue()) { //TODO Streamを使う書き方を試すべき
            if (value.startsWith("-" + ArgsStartWith.date + ":")) {
                this.date = value;

            } else if (value.startsWith("-" + ArgsStartWith.start + ":")) {
                this.start = value;

            } else if (value.startsWith("-" + ArgsStartWith.end + ":")) {
                this.end = value;

//            } else {
//                //Errorの際にどこがエラーかを出力できた方がより良い。
//                throw new Exception("inputの後には、-date・-start・-endを全て入力して下さい。");
            }
        }
//        this.date = args[1];
//        this.start = args[2];
//        this.end = args[3];
    }

    //Integer.valueOf(hoge)
    public int getIntStartH() {
        return Integer.valueOf(getStart().substring(0, 2));
    }

    public int getIntStartM() {
        return Integer.valueOf(getStart().substring(2, 4));
    }

    public int getIntEndH() {
        return Integer.valueOf(getEnd().substring(0, 2));
    }

    public int getIntEndM() {
        return Integer.valueOf(getEnd().substring(2, 4));
    }

    /*
     * 仕様変更でinput -date20180101 -start0900 -end1800 に対応するためのメソッド
     *
     */
    public String getDate() {
        return this.date.substring(6, 14);
    }

    public String getStart() {
        return this.start.substring(7, 11);
    }

    public String getEnd() {
        return this.end.substring(5, 9);
    }
}

