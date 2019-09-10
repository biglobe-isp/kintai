package kintai.domain;

public interface AttendanceRepository {
    void saveAttendance(Attendance attendance);
    Attendances findAttendancesByMonth(AttendanceMonth month);
}
