package jp.co.biglobe.isp.kintai.api;

public record InputArgs(String attendanceDate, String attendanceStartTime, String attendanceEndTime) {
    public static InputArgs create(String[] args) {

        return new InputArgs(args[0], args[1], args[2]);
    }
}
