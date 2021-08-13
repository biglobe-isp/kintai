package jp.co.esumit.kintai.api.validator;

import jp.co.esumit.kintai.datasource.AppConst;

import javax.xml.bind.ValidationException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArgsValidator {
    public void isValidArgs(String args[]) throws Exception {

        if(args.length < 1){
            throw new RuntimeException("引数が足りません。");
        }

        if(!isValidMethodType(args[0])){
            throw new ValidationException("methodTypeが不正です。");
        }
    }

    private boolean isValidMethodType(String methodType){

        return methodType.equals(AppConst.MethodType.INPUT)
                || methodType.equals(AppConst.MethodType.TOTAL);
    }

}
