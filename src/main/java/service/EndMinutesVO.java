package service;

public class EndMinutesVO {
    private final int endMinutes;

    public EndMinutesVO(ArgsVO argsVO) {
        this.endMinutes = Integer.valueOf(argsVO.getArgs()[3].substring(2, 4));
    }

    public int getEndMinutes() {
        return endMinutes;
    }
}
