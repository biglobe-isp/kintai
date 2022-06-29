package jp.co.biglobe.isp.kintai.datasource;

record DailyAttendanceCsv(
        String attendanceDate,
        String attendanceStartTime,
        String attendanceEndTime,
        String workTimeMinutes,
        String overTimeMinutes,
        String updatedAt) {
}
