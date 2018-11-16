package refactor.domain.dto.Item;

//TODO String渡すのはAPIにね
public class DateItem {
    private final String date;

    public DateItem(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }
}
