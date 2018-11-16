package refactor.api;

import refactor.api.form.ArgsItem;
import refactor.api.fuction.InputDataConverter;
import refactor.domain.dto.Item.DateItem;
import refactor.domain.dto.Item.EndTimeItem;
import refactor.domain.dto.Item.StartTimeItem;
import refactor.domain.dto.Item.YearAndMonth;
import refactor.domain.repository.NowTimeRepository;
import refactor.api.form.MethodType;
import refactor.api.fuction.AttendanceValidater;
import refactor.datasource.*;
//OK データの入力エリアをDomainパッケージ配下に置いたら、APIから直接Domainを呼んでしまっているがOKか
import refactor.domain.dto.RegistAttendanceEvent;
import refactor.domain.dto.DisplayAttendanceEvent;
import refactor.domain.dto.OutPutTotalData;
import refactor.service.AttendanceDisplayServiece;
import refactor.service.AttendanceRegistServiece;

public class AttendanceApi {
    public static void main(String[] args) {

        ArgsItem argsItem = new ArgsItem(args);
        try {
            //引数存在チェック
            AttendanceValidater.validateExsitArg(argsItem);
            String methodType = argsItem.getArgs()[0];
            //現在日付取得用レポジトリ
            NowTimeRepository nowTimeRepo = new NowTimeRepositoryImpl();

            //入力データ変換用
            InputDataConverter converter = new InputDataConverter();

            //登録時処理
            if (MethodType.input.toString().equals(methodType)) {
                AttendanceValidater.validateArgInInput(argsItem);
                //登録用入力データ生成
                RegistAttendanceEvent inputData = new RegistAttendanceEvent(methodType,
                        new DateItem(converter.convertFormToEvent(methodType,converter.INPUT_FORMAT_DATE,argsItem)),
                        new StartTimeItem(converter.convertFormToEvent(methodType,converter.INPUT_FORMAT_START,argsItem)),
                        new EndTimeItem(converter.convertFormToEvent(methodType,converter.INPUT_FORMAT_END,argsItem)),
                        nowTimeRepo);

                //登録処理
                //TODO コンストラクタで引数渡し
                AttendanceRegistServiece service = new AttendanceRegistServiece(new AttendanceRepositoryInsertImpl());

                service.inputAttendance(inputData);

                //勤務時間出力処理
            } else if (MethodType.total.toString().equals(methodType)) {
                AttendanceValidater.validateArgTotal(argsItem);
                DisplayAttendanceEvent inputDataForTotal = new DisplayAttendanceEvent(methodType,
                        new YearAndMonth(argsItem.getArgs()[1]));
                AttendanceDisplayServiece service = new AttendanceDisplayServiece(new AttendanceRepositorySelectImpl());

                OutPutTotalData outputData = service.totalAttendance(inputDataForTotal);
                System.out.println("勤務時間: " + outputData.getTotalWorkMinutes() / 60 + "時間" + outputData.getTotalWorkMinutes() % 60 + "分");
                System.out.println("残業時間: " + outputData.getTotalOverWorkMinutes() / 60 + "時間" + outputData.getTotalOverWorkMinutes() % 60 + "分");
            } else {
                throw new RuntimeException("methodTypeが不正です");
            }
        }catch(Exception e){
            e.printStackTrace();
            }
    }


}
