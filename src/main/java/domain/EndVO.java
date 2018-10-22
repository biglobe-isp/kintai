package domain;

public class EndVO {
    private final String value;

    public EndVO(String[] args) {
        this.value = args[3];
    }

    public String getValue() {
        return value;
    }
}
