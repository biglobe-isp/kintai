package com.kintai.service.usecase.impl;

import com.kintai.datasource.entity.Attendance;
import com.kintai.domain.dto.AttendanceFactoryDto;
import com.kintai.domain.factory.IAttendanceFactory;
import com.kintai.domain.repository.IAttendanceSaveRepository;
import com.kintai.exception.ValidatorException;
import com.kintai.service.usecase.IAttendanceRegisterService;
import com.kintai.service.dto.request.RequestAttendanceRegisterDto;
import com.kintai.service.dto.response.ResponseAttendanceRegisterDto;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.util.Arrays;

/**
 * 勤怠データを登録するユースケースサービスインターフェースを実装するクラス
 */
@Slf4j
public class AttendanceRegisterService implements IAttendanceRegisterService {
    // 勤怠データを登録するリポジトリインターフェース
    private final IAttendanceSaveRepository iAttendanceRepository;

    // 勤怠データ登録に使用するエンティティクラスを生成するファクトリーインターフェース
    private final IAttendanceFactory iAttendanceFactory;

    // 呼び出し元に処理結果を報告する用のメッセージ
    protected String resultMessage = "正常に勤怠が登録されました。";

    /**
     * コンストラクタ
     * 勤怠登録を実施するリポジトリインターフェース{@link IAttendanceSaveRepository}をDIします。
     * 勤怠エンティティを生成するファクトリーインターフェース{@link IAttendanceFactory}をDIします。
     * 各インターフェース実装クラスは呼び出し元から引数で連携します。
     * @param iAttendanceRepository 勤怠データ登録リポジトリインターフェース
     * @param iAttendanceFactory 勤怠エンティティを生成するファクトリーインターフェース
     */
    public AttendanceRegisterService(IAttendanceSaveRepository iAttendanceRepository, IAttendanceFactory iAttendanceFactory) {
        this.iAttendanceRepository = iAttendanceRepository;
        this.iAttendanceFactory = iAttendanceFactory;
    }

    /**
     * 勤怠データを登録します。
     * 本メソッドで登録できる勤怠データは1件です。
     * 引数のDTOを元に登録する勤怠エンティティを生成します。
     * 生成した勤怠エンティティをリポジトリクラスから登録します。
     * 登録に成功した場合は、登録が成功した旨を呼び出し元に連携。
     * <br />
     * 生成時に各値のバリデーションチェックを実施し、異常を検知した場合スローされる{@link ValidatorException}をキャッチします。
     * 登録時も想定外の例外が発生{@link Exception}した場合も同様に、本メソッドでキャッチ。想定外のため、スタックとレースをログ上に出力
     * 登録に失敗したメッセージとして、キャッチした例外が持つメッセージを呼び出し元に連携し、登録処理を中断します。
     * @param requestAttendanceRegisterDto 勤怠データの登録に必要データを格納しているDTO
     * @return 登録処理結果を呼び出し元に連携するDTO
     */
    @Override
    public ResponseAttendanceRegisterDto register(RequestAttendanceRegisterDto requestAttendanceRegisterDto) {
        try {
            Attendance attendance = makeAttendance(requestAttendanceRegisterDto);
            saveAttendance(attendance);
        } catch (ValidatorException e) {
            resultMessage = e.getMessage();
        } catch (Exception e) {
            resultMessage = e.getMessage();
            log.error(Arrays.toString(e.getStackTrace()));
        }

        return makeResponseAttendanceRegisterDto(requestAttendanceRegisterDto, resultMessage);
    }

    /**
     * 勤怠エンティティを生成します。
     * 引数の勤怠データをファクトリークラスに連携し、エンティティを生成します。
     * ファクトリークラス側でバリデーションチェックを実施するため、異常を検知した場合は例外をスローします。
     * @param requestAttendanceRegisterDto 勤怠エンティティの生成に必要なデータを格納しているDTO
     * @return 勤怠エンティティ
     * @throws ValidatorException ファクトリークラス側で実施されるバリデーションチェックで異常を検知した場合、{@link ValidatorException}をスローします。
     * @throws ParseException ファクトリークラス側で実施されるバリデーションチェックで異常を検知した場合、{@link ParseException}をスローします。
     */
    protected Attendance makeAttendance(RequestAttendanceRegisterDto requestAttendanceRegisterDto) throws ValidatorException, ParseException {
        return iAttendanceFactory.makeAttendance(
                new AttendanceFactoryDto(
                        requestAttendanceRegisterDto.getWorkDate(),
                        requestAttendanceRegisterDto.getStartTime(),
                        requestAttendanceRegisterDto.getEndTime()
                )
        );
    }

    /**
     * 勤怠データを登録します。
     * 勤怠データを登録するリポジトリクラス側で想定外の例外を検知した場合はExceptionをスローします。
     * @param attendance 勤怠エンティティ
     * @throws Exception 想定外を検知した場合、{@link Exception}をスローします。
     */
    protected void saveAttendance(Attendance attendance) throws Exception {
        iAttendanceRepository.save(attendance);
    }

    /**
     * 呼び出し元に処理結果を伝えるためのDTOを生成します。
     * @param requestAttendanceRegisterDto 呼び出し元から受け取ったDTO
     * @param resultMessage 処理結果メッセージ
     * @return 処理結果DTO
     */
    protected ResponseAttendanceRegisterDto makeResponseAttendanceRegisterDto(RequestAttendanceRegisterDto requestAttendanceRegisterDto, String resultMessage) {
        return new ResponseAttendanceRegisterDto(
                requestAttendanceRegisterDto.getWorkDate(),
                requestAttendanceRegisterDto.getStartTime(),
                requestAttendanceRegisterDto.getEndTime(),
                resultMessage
        );
    }
}
