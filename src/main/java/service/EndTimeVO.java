package service;

public class EndTimeVO {
    private final int endTime;

    public EndTimeVO(EndHourVO endHourVO, EndMinutesVO endMinutesVO) {
        this.endTime = endHourVO.getEndHour() * 60 + endMinutesVO.getEndMinutes();
    }

    public int getEndTime() {
        return endTime;

    }

//    public int getBreakTime() {
//        int breakTime;
//        return 0;
//    }
}
