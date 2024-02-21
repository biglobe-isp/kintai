package com.kintai.service.usecase.impl.expansion.register;

import com.kintai.datasource.value.expansion.password.CsvPassword;
import com.kintai.domain.factory.IAttendanceFactory;
import com.kintai.domain.repository.IAttendanceSaveRepository;
import com.kintai.exception.ValidatorException;
import com.kintai.service.dto.request.RequestAttendanceRegisterDto;
import com.kintai.service.dto.response.ResponseAttendanceRegisterDto;
import com.kintai.service.usecase.IAttendanceRegisterService;
import com.kintai.service.usecase.impl.AttendanceRegisterService;

/**
 * 勤怠登録ユースケースサービスV1
 */
public class AttendanceRegisterServiceV1 extends AttendanceRegisterService implements IAttendanceRegisterService {
    /**
     * コンストラクタ
     * @param iAttendanceSaveRepository 勤怠登録リポジトリ
     * @param iAttendanceFactory 勤怠エンティティ生成ファクトリー
     */
    public AttendanceRegisterServiceV1(IAttendanceSaveRepository iAttendanceSaveRepository, IAttendanceFactory iAttendanceFactory) {
        super(iAttendanceSaveRepository, iAttendanceFactory);
    }

    /**
     * 勤怠登録メソッド
     * @param requestAttendanceRegisterDto 勤怠登録リクエストDTO
     * @return 勤怠登録レスポンスDTO
     */
    @Override
    public ResponseAttendanceRegisterDto register(RequestAttendanceRegisterDto requestAttendanceRegisterDto) {
        ResponseAttendanceRegisterDto responseAttendanceRegisterDto;
        try {
            // パスワード認証
            authPassword(requestAttendanceRegisterDto.getPassword());
            // 勤怠登録は親クラス側で実施
            responseAttendanceRegisterDto = super.register(requestAttendanceRegisterDto);
        } catch (Exception e) {
            resultMessage = e.getMessage();
            responseAttendanceRegisterDto = makeResponseAttendanceRegisterDto(requestAttendanceRegisterDto, resultMessage);
        }
        return responseAttendanceRegisterDto;
    }

    /**
     * パスワード認証メソッド
     * 引数で受け取ったパスワードが正しいかどうか確認する
     * @param password パスワード
     * @throws ValidatorException チェック結果に問題がある場合は例外をスローする
     */
    protected void authPassword(String password) throws ValidatorException {
        new CsvPassword(password);
    }
}
