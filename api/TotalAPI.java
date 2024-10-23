package com.naosim.dddwork.api;

import com.naosim.dddwork.api.dto.TotalAPIResponseDTO;
import com.naosim.dddwork.domain.WorkTimeSpecification;
import com.naosim.dddwork.service.TotalService;

public class TotalAPI {
    private final TotalService service = new TotalService();

    public TotalAPIResponseDTO get(String[] args){
        TotalAPIResponseDTO responseDTO = new TotalAPIResponseDTO();
        responseDTO.result_code = 200;
        responseDTO.result_msg = "OK";

        try{
             WorkTimeSpecification spec = service.getMonthlyTotal();
             responseDTO.workMinutesSum = spec.workMinutes;
             responseDTO.overWorkMinutesSum = spec.overWorkMinutes;
        }
        catch (Exception e){
            responseDTO.result_code = 400;
            responseDTO.result_msg = e.getMessage();
        }

        return responseDTO;
    }
}
