package com.naosim.dddwork;

import com.naosim.dddwork.api.InputAPI;
import com.naosim.dddwork.api.TotalAPI;
import com.naosim.dddwork.api.dto.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
            if (args.length < 1) {
                throw new RuntimeException("クライアントエラー。引数が足りません");
            }
            String methodType = args[0];

            switch (methodType)
            {
                case "input":
                    {
                        InputAPI api = new InputAPI();

                        ArrayList<String> argsCopy = new ArrayList<>(Arrays.asList(args));
                        argsCopy.removeFirst();
                        InputAPIResponseDTO responseDTO = api.get(argsCopy.toArray(new String[argsCopy.size()]));
                        if(responseDTO.result_code != 200)
                        {
                            throw new Exception("APIエラー。result_code : " + responseDTO.result_code + ", result_msg : " + responseDTO.result_msg);
                        }

                        System.out.println("登録完了");
                    }
                    break;
                case "total":
                    {
                        TotalAPI api = new TotalAPI();

                        ArrayList<String> argsCopy = new ArrayList<>(Arrays.asList(args));
                        argsCopy.removeFirst();
                        TotalAPIResponseDTO responseDTO = api.get(argsCopy.toArray(new String[argsCopy.size()]));
                        if(responseDTO.result_code != 200)
                        {
                            throw new Exception("APIエラー。result_code : " + responseDTO.result_code + ", result_msg : " + responseDTO.result_msg);
                        }

                        System.out.println("勤務時間: " + responseDTO.workMinutesSum / 60 + "時間" + responseDTO.workMinutesSum % 60 + "分");
                        System.out.println("残業時間: " + responseDTO.overWorkMinutesSum / 60 + "時間" + responseDTO.overWorkMinutesSum % 60 + "分");
                    }
                    break;
                default:
                    throw new Exception("クライアントエラー。存在しないAPIです。呼ばれたAPI名 : " + methodType);
            }
        } catch (Exception e){
            System.out.println("===============エラー===============");
            e.getMessage();
            e.printStackTrace();
        }
    }
}
