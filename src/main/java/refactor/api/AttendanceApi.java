package refactor.api;

import refactor.domain.dto.Item.ArgsItem;
import refactor.domain.repository.AttendanceRepositoryInsert;
import refactor.domain.repository.AttendanceRepositorySelect;
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

            //登録時処理
            if (MethodType.input.toString().equals(methodType)) {
                AttendanceValidater.validateArgInInput(argsItem);
                //登録用入力データ生成
                RegistAttendanceEvent inputData = new RegistAttendanceEvent(argsItem,nowTimeRepo);

                //登録処理
                //TODO コンストラクタで引数渡し
                AttendanceRegistServiece service = new AttendanceRegistServiece(new AttendanceRepositoryInsertImpl());

                service.inputAttendance(inputData);

                //勤務時間出力処理
            } else if (MethodType.total.toString().equals(methodType)) {
                AttendanceValidater.validateArgTotal(argsItem);
                DisplayAttendanceEvent inputDataForTotal = new DisplayAttendanceEvent(argsItem);
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
