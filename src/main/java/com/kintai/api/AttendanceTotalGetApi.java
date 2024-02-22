package com.kintai.api;

import com.kintai.datasource.repository.CsvAttendanceGetRepository;
import com.kintai.domain.factory.impl.CsvAttendanceFactory;
import com.kintai.domain.service.impl.TotalDomainService;
import com.kintai.service.dto.request.RequestAttendanceGetDto;
import com.kintai.service.usecase.IAttendanceTotalGetService;
import com.kintai.service.dto.response.ResponseAttendanceTotalGetDto;
import com.kintai.service.usecase.impl.AttendanceTotalGetService;
import lombok.extern.slf4j.Slf4j;

/**
 * 勤怠集計API
 * 今まで登録されている勤怠データを集計し、ログ上に出力します。
 */
@Slf4j
public class AttendanceTotalGetApi {
    // 勤怠集計ユースケースサービス
    private final IAttendanceTotalGetService iAttendanceTotalGetService;

    /**
     * コンストラクタ
     * 勤怠を集計するユースケースインターフェース{@link IAttendanceTotalGetService}をDIします。
     * DIの際は勤怠データを取得するリポジトリクラス{@link CsvAttendanceGetRepository}、
     * 勤怠エンティティを生成するファクトリークラス{@link CsvAttendanceFactory}、
     * 勤怠データを集計するドメインサービスクラス{@link TotalDomainService}を引数として連携します。
     */
    public AttendanceTotalGetApi() {
        this.iAttendanceTotalGetService = new AttendanceTotalGetService(new CsvAttendanceGetRepository(new CsvAttendanceFactory()), new TotalDomainService());
    }

    /**
     * 集計された勤怠データを取得するメソッド
     * @param args 入力値　各値は以下の通り
     *             args[0]：DB登録パスワード(アプリ実行時の曜日※を入力)
     */
    public void getTotal(String[] args) {
        log.info("勤怠集計処理を開始します。");
        RequestAttendanceGetDto requestAttendanceGetDto = makeRequestAttendanceGetDto(args);

        // 勤怠集計データ取得
        ResponseAttendanceTotalGetDto responseAttendanceTotalGetDto = iAttendanceTotalGetService.get(requestAttendanceGetDto);

        // 取得結果をログに出力
        log.info(responseAttendanceTotalGetDto.toString());
        log.info("勤怠集計処理を終了します。");
    }

    /**
     * ユースケースに連携するリクエストDTOを生成します。
     * @param args 入力値
     * @return リクエストDTO
     */
    protected RequestAttendanceGetDto makeRequestAttendanceGetDto(String[] args) {
        return new RequestAttendanceGetDto(args[0]);
    }
}
