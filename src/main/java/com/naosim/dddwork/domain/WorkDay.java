package com.naosim.dddwork.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@RequiredArgsConstructor
public class WorkDay {
    @Getter
    private final LocalDate value;
}
