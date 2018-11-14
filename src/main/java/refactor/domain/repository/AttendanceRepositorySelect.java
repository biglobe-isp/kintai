package refactor.api.repository;

import refactor.domain.dto.DisplayAttendanceEvent;
import refactor.domain.dto.WorkMinutes;

public interface AttendanceRepositorySelect {
    public WorkMinutes select(DisplayAttendanceEvent data);
}
