package jp.co.biglobe.kintai.util;

public final class Time {

    private final int hours;


    private final int minutes;


    public Time(int hours, int minutes){
        this.hours = hours;
        this.minutes = minutes;
    }

    public Time(String time){
        this.hours = Integer.valueOf(time.substring(0,2));
        this.minutes = Integer.valueOf(time.substring(2,4));
    }

    public Time(int conversionMinute){
        this.hours = conversionMinute / 60;
        this.minutes = conversionMinute % 60;
    }

    public static Time add(Time base, Time value){
        int minutes = base.minutes + value.minutes;
        int hours = base.hours + value.hours;
        if(minutes >= 60){
            hours += minutes / 60;
            minutes = minutes % 60;
        }
        return new Time(hours,minutes);
    }

    public static Time sub(Time base, Time value){
        int minutes = base.minutes - value.minutes;
        int hours = base.hours - value.hours;
        if(minutes < 0){
            hours--;
            minutes += 60;
        }
        return new Time(hours,minutes);
    }

    public int getAbsoluteMinutes(){
        int minutues = 0;
        if(this.hours<0){
            minutues += this.hours*-60;
        }else{
            minutues += this.hours*60;
        }

        return minutes+this.minutes;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }
}
