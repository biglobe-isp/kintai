package com.naosim.dddwork.service.dto.total;

public class GetMonthlyTotalResponseDTO {
    public Integer workTimeMinutesSum;
    public Integer overWorkTimeMinutesSum;

    public GetMonthlyTotalResponseDTO(Integer workTimeMinutesSum, Integer overWorkTimeMinutesSum) {
        this.workTimeMinutesSum = workTimeMinutesSum;
        this.overWorkTimeMinutesSum = overWorkTimeMinutesSum;
    }
}
