package refactor.api.fuction;

import refactor.api.form.ArgsItem;
import refactor.api.form.MethodType;
import refactor.domain.dto.Item.DateItem;
import refactor.domain.dto.Item.EndTimeItem;
import refactor.domain.dto.Item.StartTimeItem;


public class InputDataConverter {

    private final String INPUT_REGEX =":";
    private final String TIME_REGEX ="_";
    private final String INPUT_TYPE_PREFIX ="-";

    public final String INPUT_FORMAT_DATE ="date";
    public final String INPUT_FORMAT_START = "start";
    public final String INPUT_FORMAT_END ="end";


    /**
     * 入力データをDomain層で処理する為のデータへ詰め替える
     * @param methodType
     * @param args
     * @return
     */
    public String convertFormToEvent(String methodType,String inputType, ArgsItem args){
        if (MethodType.input.toString().equals(methodType)) {
            //登録機能の場合の変換処理
            return convertFormToEventForInput(inputType,args);
        }

//        if (MethodType.total.toString().equals(methodType)) {
//
//        }
        throw new RuntimeException("methodTypeが不正です");
    }

    //TODO　Stream使うと綺麗に書けるよ
    private String convertFormToEventForInput(String inputType,ArgsItem args){

        //TODO ここ多分１だよ。
        final int IDX_TYPE_IN_LIST = 0;
        final int IDX_DATA_IN_LIST = 1;

        for(int i = 1 ; i < args.getArgs().length ; i++) {
            String inputData = args.getArgs()[i];
            String[] inputDataList = inputData.split(INPUT_REGEX);

            //分割後の形式チェック
            validateInputFormat(inputDataList);

            if(inputDataList[IDX_TYPE_IN_LIST].equals(INPUT_TYPE_PREFIX+inputType)){
//                System.out.println(inputType);
                if(!INPUT_FORMAT_DATE.equals(inputType)){
                    return convertTimeFormat(inputDataList[IDX_DATA_IN_LIST]);
                }
                return inputDataList[IDX_DATA_IN_LIST];
            }
//            System.out.println(inputData);

        }
        throw new RuntimeException("入力形式が不正です");

    }



    private void validateInputFormat(String[] strs){
        if (strs.length != 2) {
            throw new RuntimeException("入力形式が不正です");
        }
    }

    private String convertTimeFormat(String str){
        return str.replace(TIME_REGEX,"");
    }
}
