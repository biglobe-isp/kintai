package api;

public class TimeApi {
    public int getIntHour(String hour) {
        return Integer.valueOf(hour.substring(0, 2));
    }

    public int getIntMinuts(String minuts) {
        return Integer.valueOf(minuts.substring(2, 4));
    }
}
