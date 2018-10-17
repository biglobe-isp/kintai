package service;

public class StartHourVO {
    private final int startHour;

    public StartHourVO(ArgsVO argsVO) {
        this.startHour = Integer.valueOf(argsVO.getArgs()[2].substring(0, 2));
        //Integer.valueOf(start.substring(0, 2));
    }

    public int getStartHour() {
        return startHour;
    }
}
