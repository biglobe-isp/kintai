package kintai.api;

public class KintaiAggregateRequest {

    String referYearMonth;

    public KintaiAggregateRequest(String arg1) {
        this.referYearMonth = arg1;
    }

    public String getArg1() {
        return referYearMonth;
    }

}
