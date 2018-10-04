package jp.co.biglobe.kintai.domain;

public class StartWorkTime {

    private final String time;

    public StartWorkTime(String time){
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public int getHoursAsInt(){
        return Integer.valueOf(time.substring(0,2));
    }

    public int getMinutesAsInt(){
        return Integer.valueOf(time.substring(2,4));
    }
}
