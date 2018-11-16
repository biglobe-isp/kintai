package refactor.domain.dto;

import refactor.domain.dto.Item.YearAndMonth;


public class DisplayAttendanceEvent {
    private final String methodType;

    private final YearAndMonth yearMonth;
//TODO DOMAIN層の引数はにしない
    public DisplayAttendanceEvent(String methodTypeInput,YearAndMonth yearMonthInput ) {
        methodType = methodTypeInput;
        yearMonth = yearMonthInput;
    }

    public YearAndMonth getYearMonth() {
        return yearMonth;
    }

    public String getMethodType() {
        return methodType;
    }

}
