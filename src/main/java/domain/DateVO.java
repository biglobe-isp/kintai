package domain;

public class DateVO {
    private final String value;
    //private final String now = LocalDateTime.now().toString(); //TODO dateVOに含めるより適切な箇所はあるか。

    public DateVO(String value) {
        this.value = value;
        //this.now = LocalDateTime.now().toString(); //TODO コンストラクタかフィールドかgetterのreturn部、どれに書くのがより良いか。
    }

    public String getValue() {
        return value;
    }

//    public String getNow() {
//        return now;
//    }
}
