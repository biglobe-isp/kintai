package refactor.domain.dto;

import refactor.domain.dto.Item.ArgsItem;

public class DisplayAttendanceEvent {
    private final String MethodType;

    private final String yearMonth;
//TODO DOMAIN層の引数はStringにしない
    public DisplayAttendanceEvent(ArgsItem argsItem) {
        String[] args = argsItem.getArgs();
        MethodType = args[0];
        yearMonth = args[1];
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public String getMethodType() {
        return MethodType;
    }

}
