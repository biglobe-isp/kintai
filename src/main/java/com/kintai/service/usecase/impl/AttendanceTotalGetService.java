package com.kintai.service.usecase.impl;

import com.kintai.datasource.entity.Attendance;
import com.kintai.datasource.entity.WorkTotal;
import com.kintai.datasource.value.expansion.password.CsvPassword;
import com.kintai.domain.repository.IAttendanceGetRepository;
import com.kintai.domain.service.ITotalDomainService;
import com.kintai.exception.ValidatorException;
import com.kintai.service.dto.request.RequestAttendanceGetDto;
import com.kintai.service.usecase.IAttendanceTotalGetService;
import com.kintai.service.dto.response.ResponseAttendanceTotalGetDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 勤怠データを集計するユースケースサービスインターフェースを実装するクラス
 */
@Slf4j
public class AttendanceTotalGetService implements IAttendanceTotalGetService {
    // 勤怠データを取得するリポジトリインターフェース
    private final IAttendanceGetRepository iAttendanceGetRepository;

    // 勤怠データを集計するドメインサービスインターフェース
    private final ITotalDomainService iTotalDomainService;

    // 呼び出し元に処理結果を報告する用のメッセージ
    private String resultMessage = "正常に勤怠データを取得されました。";

    /**
     * コンストラクタ
     * 勤怠データ取得するリポジトリインターフェース{@link IAttendanceGetRepository}をDIします。
     * 勤怠データをっ集計するドメインサービスインターフェース{@link ITotalDomainService}をDIします。
     * 各インターフェース実装クラスは呼び出し元から引数で連携します。
     * @param iAttendanceGetRepository 勤怠データ取得リポジトリインターフェース
     * @param iTotalDomainService 勤怠データ集計ドメインサービスインターフェース
     */
    public AttendanceTotalGetService(IAttendanceGetRepository iAttendanceGetRepository, ITotalDomainService iTotalDomainService) {
        this.iAttendanceGetRepository = iAttendanceGetRepository;
        this.iTotalDomainService = iTotalDomainService;
    }

    /**
     * 勤怠データを集計したものを取得します。
     * 本メソッドは勤怠取得に条件を付けないため、全勤怠データを集計したものを取得します。
     * 勤怠データをリポジトリクラスから取得し、その結果を集計したものを呼び出し元に連携します。
     * 取得に成功した場合は、取得が成功した旨も呼び出し元に連携。
     * <br />
     * 勤怠データの取得に失敗した場合は例外が呼び出し先からスロー{@link Exception}されるため、キャッチした例外が持つメッセージを呼び出し元に連携します。
     * @return 取得処理結果を呼び出し元に連携するDTO
     */
    @Override
    public ResponseAttendanceTotalGetDto get() {
        List<Attendance> attendanceList = null;
        try {
            attendanceList = getAttendanceList();
        } catch (Exception e) {
            resultMessage = e.getMessage();
            log.error(Arrays.toString(e.getStackTrace()));
        }

        List<WorkTotal> workTotalList = summaryMonthlyTotal(attendanceList);
        return makeResponseAttendanceTotalGetDto(workTotalList, resultMessage);
    }

    /**
     * 勤怠データを集計したものを取得します。
     * 本メソッドは呼び出し元からリポジトリを操作するためのパスワードが連携されるため、パスワードが一致するかチェック。
     * パスワードが一致する場合は、勤怠データ取得処理({@link AttendanceTotalGetService#get()})を呼び出し、集計した勤怠データを取得します。
     * パスワードが不一致の場合は、チェックを実施する処理から例外がスロー{@link ValidatorException}されるため、キャッチしたメッセージを呼び出し元に連携し、取得処理を中断します。
     * @param requestAttendanceGetDto 勤怠データの集計に必要なデータを格納しているDTO
     * @return 取得処理結果を呼び出し元に連携するDTO
     */
    @Override
    public ResponseAttendanceTotalGetDto get(RequestAttendanceGetDto requestAttendanceGetDto) {
        ResponseAttendanceTotalGetDto responseAttendanceTotalGetDto;
        try {
            authPassword(requestAttendanceGetDto.getPassword());
            responseAttendanceTotalGetDto = get();
        } catch (ValidatorException e) {
            resultMessage = e.getMessage();
            responseAttendanceTotalGetDto = makeResponseAttendanceTotalGetDto(summaryMonthlyTotal(null), resultMessage);
        }
        return responseAttendanceTotalGetDto;
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

    /**
     * 勤怠データをリポジトリから取得します。
     * @return 勤怠データリスト
     * @throws Exception 想定外を検知した場合、{@link Exception}をスローします。
     */
    protected List<Attendance> getAttendanceList() throws Exception {
        return iAttendanceGetRepository.get();
    }

    /**
     * 勤怠データを月単位に集計します。
     * 集計する勤怠データがnullまたは0件の場合は空のインスタンスを生成し、処理を終了します。
     * 1件以上勤怠データがある場合は、ドメインサービスを用いて勤怠データを集計します。
     * @param attendanceList 集計対象の勤怠データリスト
     * @return 集計した勤怠データリスト
     */
    protected List<WorkTotal> summaryMonthlyTotal(List<Attendance> attendanceList) {
        if (CollectionUtils.isEmpty(attendanceList)) {
            return new ArrayList<>();
        }
        return iTotalDomainService.getMonthlyTotalList(attendanceList);
    }

    /**
     * 呼び出し元に処理結果を伝えるためのDTOを生成します。
     * @param workTotalList 呼び出し元から受け取ったDTO
     * @param resultMessage 処理結果メッセージ
     * @return 処理結果DTO
     */
    protected ResponseAttendanceTotalGetDto makeResponseAttendanceTotalGetDto(List<WorkTotal> workTotalList, String resultMessage) {
        return new ResponseAttendanceTotalGetDto(workTotalList, resultMessage);
    }
}
