package jp.co.esumit.kintai.api.validator;

import jp.co.esumit.kintai.datasource.AppConst;

import javax.xml.bind.ValidationException;
import java.util.Optional;

public class ArgsValidator {
    public void isValidArgs(String args[]) throws Exception {

        if(!isValidArgsCount(args)){
            throw new RuntimeException("引数が足りません。");
        }

        if(!isValidMethodType(args[0])){
            throw new ValidationException("methodTypeが不正です。");
        }


    }

    private boolean isValidArgsCount(String args[]){

        return args.length == AppConst.ArgsCount.INPUT_CNT
                || args.length == AppConst.ArgsCount.TOTAL_CNT;

    }

    private boolean isValidMethodType(String methodType){

        return methodType.equals(AppConst.MethodType.INPUT)
                || methodType.equals(AppConst.MethodType.TOTAL);
    }
}
