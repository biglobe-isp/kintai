package refactor.domain.repository;

import refactor.domain.dto.DisplayAttendanceEvent;
import refactor.domain.dto.WorkAndOverWorkMinutesList;

public interface AttendanceRepositorySelect {
    public WorkAndOverWorkMinutesList select(DisplayAttendanceEvent data);
}
