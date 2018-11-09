package refactor.api;

import refactor.api.form.InputData;
import refactor.api.form.MethodType;
import refactor.api.fuction.AttendanceFunction;
import refactor.service.AttendanceServiece;

import java.time.LocalDateTime;

public class AttendanceApi {
    public static void main(String[] args) {

        try {
            //引数存在チェック
            AttendanceFunction.validateExsitArg(args);
            String methodType = args[0];
            //登録時処理
            if (MethodType.input.toString().equals(methodType)) {
                //入力データを内部データへ変換
                InputData inputData = convertForm(args);

                //登録処理
                AttendanceServiece service = new AttendanceServiece();
                service.inputAttendance(inputData);

                //勤務時間出力処理
            } else if (MethodType.total.toString().equals(methodType)) {

            } else {
                throw new RuntimeException("methodTypeが不正です");
            }
        }catch(Exception e){
            e.printStackTrace();
            }
    }
    private static InputData convertForm(String[] args){
        InputData inputData =new InputData();
        //日付
        inputData.setDate(args[1]);
        //始業時間
        inputData.setStartTime(args[2]);
        //終業時間
        inputData.setEndTime(args[3]);
        //現在時刻
        inputData.setNowTime(LocalDateTime.now().toString());

        return inputData;
    }


}
