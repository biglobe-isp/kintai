package refactor.api.repository;

import refactor.domain.dto.AttendanceData;
//DOMAIN層

public interface AttendanceRepositoryInsert {
    public void insert(AttendanceData data);
}
