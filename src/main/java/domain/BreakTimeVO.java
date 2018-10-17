//WorkTimeVOのコンストラクタの処理に記述。

//package domain;
//
//public class BreakTimeVO {
//    //private final int breakTime;
//    private int breakTime;
//
//    public BreakTimeVO(EndHourVO endHourVO, EndMinutesVO endMinutesVO) {
//
//        if (endHourVO.getEndHour() == 12) {
//            this.breakTime = -endMinutesVO.getEndMinutes();
//        } else if (13 <= endHourVO.getEndHour()) {
//            this.breakTime = -60;
//        }
//
//        if (endHourVO.getEndHour() == 18) {
//            this.breakTime = -endMinutesVO.getEndMinutes();
//        } else if (19 <= endHourVO.getEndHour()) {
//            this.breakTime = -60;
//        }
//
//        if (endHourVO.getEndHour() == 21) {
//            this.breakTime = -endMinutesVO.getEndMinutes(); //TODO エラーを解決する
//        } else if (24 <= endHourVO.getEndHour()) {
//            this.breakTime = -60;
//        }
//
//    }
//
//    public int getBreakTime() {
//        return breakTime;
//    }
//}
