package com.kintai.service.usecase;

import com.kintai.service.dto.request.RequestAttendanceRegisterDto;
import com.kintai.service.dto.response.ResponseAttendanceRegisterDto;

public interface IAttendanceRegisterService {
    ResponseAttendanceRegisterDto register(RequestAttendanceRegisterDto requestAttendanceRegisterDto);
}
