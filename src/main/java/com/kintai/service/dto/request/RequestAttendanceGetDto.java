package com.kintai.service.dto.request;

import lombok.Getter;

public class RequestAttendanceGetDto {
    @Getter
    private String password;

    public RequestAttendanceGetDto(String password) {
        this.password = password;
    }
}
