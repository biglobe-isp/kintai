package com.naosim.dddwork.api;

import com.naosim.dddwork.api.dto.InputAPIResponseDTO;
import com.naosim.dddwork.service.InputService;

import java.time.LocalDate;
import java.time.LocalTime;

public class InputAPI {
    private final InputService service = new InputService();

    public InputAPIResponseDTO get(String[] args){
        InputAPIResponseDTO responseDTO = new InputAPIResponseDTO();
        responseDTO.result_code = 200;
        responseDTO.result_msg = "OK";

        try{
            LocalDate date = LocalDate.of(Integer.parseInt(args[0].substring(0, 4)), Integer.parseInt(args[0].substring(4, 6)), Integer.parseInt(args[0].substring(6, 8)));
            LocalTime startTime = LocalTime.of(Integer.parseInt(args[1].substring(0, 2)), Integer.parseInt(args[1].substring(2, 4)));
            LocalTime endTime = LocalTime.of(Integer.parseInt(args[2].substring(0, 2)), Integer.parseInt(args[2].substring(2, 4)));

            service.registDailyWorkInfo(date, startTime, endTime);
        }
        catch (Exception e){
            responseDTO.result_code = 400;
            responseDTO.result_msg = e.getMessage();
        }

        return responseDTO;
    }
}
