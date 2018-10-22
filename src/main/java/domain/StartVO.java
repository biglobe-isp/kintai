package domain;

public class StartVO {
    private final String value;
    //private final String end;

    public StartVO(String[] args) {
        this.value = args[2];
        //this.end = args[3];
    }

    public String getValue() {
        return value;
    }

//    public String getValue() {
//        return end;
//    }
}
