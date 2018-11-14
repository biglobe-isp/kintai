package refactor.domain.dto.Item;

//Extendsせず最後にリファクタする際に上位クラスなど作るほうが良い
public class StartTimeItem {

    private int hour;

    private int minutes;

    public StartTimeItem(String hhmm) {
        this.hour = Integer.valueOf(hhmm.substring(0, 2));
        this.minutes = Integer.valueOf(hhmm.substring(2, 4));
    }

    public int getHour() {
        return hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public String valueOfHHMM(){
        return String.format("%02d",hour)+String.format("%02d",minutes);
    }
}
