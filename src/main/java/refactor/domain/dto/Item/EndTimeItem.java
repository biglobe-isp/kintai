package refactor.domain.dto.Item;

public class EndTimeItem{

    private int hour;

    private int minutes;

    public EndTimeItem(String hhmm) {
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
