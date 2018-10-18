package domain;

public class StartTimeVO {
    private final String start;
    private final int startHour;
    private final int startMinute;

    public StartTimeVO(String start) { //TimeVOがHourとMinuteを持つようにした方が構造的。
        //this.start = start.substring(7);
        this.start = start;
        this.startHour = Integer.valueOf(start.substring(0, 2)); //TODO Domain層にこれは違う。Datasource層が正しい。
        this.startMinute = Integer.valueOf(start.substring(2, 4));
    }

    public String getStart() {
        return start;
    }

    public int getStartTotalMinute() {
        return startHour * 60 + startMinute;
    }

//    public int getStartHour() {
//        return startHour;
//    }
//
//    public int getStartMinute() {
//        return startMinute;
//    }
}
