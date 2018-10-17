package service;

public class StartMinutesVO {

    private final int startMinutes;

    public StartMinutesVO(ArgsVO argsVO) {
        this.startMinutes = Integer.valueOf(argsVO.getArgs()[2].substring(2, 4));
        //Integer.valueOf(start.substring(2, 4));
    }

    public int getStartMinutes() {
        return startMinutes;
    }
}
