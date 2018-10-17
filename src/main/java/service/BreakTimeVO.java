package service;

public class BreakTimeVO {
    //private final int breakTime;
    private int breakTime;

    public BreakTimeVO(EndHourVO endHourVO, EndMinutesVO endMinutesVO) {

        if (endHourVO.getEndHour() == 12) {
            this.breakTime = -endMinutesVO.getEndMinutes();
        } else if (endHourVO.getEndHour() >= 13) {
            this.breakTime = -60;
        }

        if (endHourVO.getEndHour() == 18) {
            this.breakTime = -endMinutesVO.getEndMinutes();
        } else if (endHourVO.getEndHour() >= 19) {
            this.breakTime = -60;
        }

        if (endHourVO.getEndHour() == 21) {
            this.breakTime = -endMinutesVO.getEndMinutes(); //TODO エラーを解決する
        } else if (endHourVO.getEndHour() >= 22) {
            this.breakTime = -60;
        }

    }

    public int getBreakTime() {
        return breakTime;
    }
}
