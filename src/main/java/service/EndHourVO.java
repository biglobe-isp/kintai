package service;

public class EndHourVO {
    private final int endHour;

    public EndHourVO(ArgsVO argsVO) {
        this.endHour = Integer.valueOf(argsVO.getArgs()[3].substring(0, 2));
    }

    public int getEndHour() {
        return endHour;
    }
}
