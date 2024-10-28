package com.naosim.dddwork.api;

import com.naosim.dddwork.api.dto.TotalAPIResponseDTO;
import com.naosim.dddwork.service.TotalData;
import com.naosim.dddwork.service.TotalService;

public class TotalAPI {
    private final TotalService service = new TotalService();

    public TotalAPIResponseDTO get(String[] args){
        TotalAPIResponseDTO responseDTO = new TotalAPIResponseDTO();
        responseDTO.result_code = 200;
        responseDTO.result_msg = "OK";

        try{
             TotalData spec = service.getMonthlyTotal();
             responseDTO.workMinutesSum = spec.workTimeMinutesSum;
             responseDTO.overWorkMinutesSum = spec.overWorkTimeMinutesSum;
        }
        catch (Exception e){
            responseDTO.result_code = 400;
            responseDTO.result_msg = e.getMessage();
        }

        return responseDTO;
    }
}
