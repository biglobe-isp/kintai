package refactor.api.form;

public class DisplayAttendanceEvent {
    private final String MethodType;

    private final String yearMonth;

    public DisplayAttendanceEvent(String[] args) {
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
