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
 * 勤怠データを登録するユースケースサービスインターフェースを実装するクラス
 * 呼び出し元から連携されるリポジトリ操作用のパスワードが連携されるため、パスワードが一致するかチェックします。
 * パスワードが一致後は、親クラスである{@link AttendanceRegisterService}に登録処理を一任します。
 */
public class AttendanceRegisterServiceV1 extends AttendanceRegisterService implements IAttendanceRegisterService {
    /**
     * コンストラクタ
     * 勤怠登録を実施するリポジトリインターフェース{@link IAttendanceSaveRepository}をDIします。
     * 勤怠エンティティを生成するファクトリーインターフェース{@link IAttendanceFactory}をDIします。
     * 各インターフェース実装クラスは呼び出し元から引数で連携します。
     * @param iAttendanceSaveRepository 勤怠データ登録リポジトリインターフェース
     * @param iAttendanceFactory 勤怠エンティティを生成するファクトリーインターフェース
     */
    public AttendanceRegisterServiceV1(IAttendanceSaveRepository iAttendanceSaveRepository, IAttendanceFactory iAttendanceFactory) {
        super(iAttendanceSaveRepository, iAttendanceFactory);
    }

    /**
     * 勤怠登録をするメソッド
     * 本メソッドは呼び出し元からリポジトリを操作するためのパスワードが連携されるため、パスワードが一致するかチェック。
     * パスワードが一致する場合は、勤怠データ取得処理({@link AttendanceRegisterService#register(RequestAttendanceRegisterDto)})を呼び出し、勤怠データを登録します。
     * パスワードが不一致の場合は、チェックを実施する処理から例外がスロー{@link ValidatorException}されるため、キャッチしたメッセージを呼び出し元に連携し、登録処理を中断します。
     * @param requestAttendanceRegisterDto 勤怠データの登録に必要データを格納したDTO※パスワードもDTO内に格納されている。
     * @return 登録処理結果を呼び出し元に連携するDTO
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
     * 引数で受け取ったパスワードが正しいかどうか確認します。
     * @param password パスワード
     * @throws ValidatorException チェック結果に問題がある場合、{@link ValidatorException}をスローします。
     */
    protected void authPassword(String password) throws ValidatorException {
        new CsvPassword(password);
    }
}
