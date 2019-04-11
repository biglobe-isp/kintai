package com.naosim.dddwork.datasource;

import com.naosim.dddwork.domain.Attendance;
import com.naosim.dddwork.domain.AttendanceRepository;
import com.naosim.dddwork.domain.YearMonth;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@Repository
public class AttendanceRepositoryCsv implements AttendanceRepository {

    @Override
    public void save(Attendance attendance) {
        // TODO: Not Implemented
        throw new NotImplementedException();
    }

    @Override
    public List<Attendance> fetchMonthly(YearMonth yearMonth) {
        // TODO: Not Implemented
        throw new NotImplementedException();
    }
}
