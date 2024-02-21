package com.kintai.service.usecase;

import com.kintai.service.dto.request.RequestAttendanceGetDto;
import com.kintai.service.dto.response.ResponseAttendanceTotalGetDto;

public interface IAttendanceTotalGetService {
    ResponseAttendanceTotalGetDto get();

    ResponseAttendanceTotalGetDto get(RequestAttendanceGetDto requestAttendanceGetV1Dto);

}
