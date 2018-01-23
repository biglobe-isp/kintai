package com.naosim.dddwork.domain;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class WorkTimeInputForm {

    @Getter
    private final String date;

    @Getter
    private final String start;

    @Getter
    private final String end;

    @Getter
    private final String now;
}
