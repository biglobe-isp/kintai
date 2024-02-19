package com.kintai.domain.repository;

import com.kintai.datasource.entity.Attendance;

import java.util.List;

public interface IAttendanceGetRepository {
    List<Attendance> get() throws Exception;
}
