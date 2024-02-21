package com.kintai.api;

import com.kintai.datasource.repository.CsvAttendanceSaveRepository;
import com.kintai.domain.factory.impl.AttendanceFactory;
import com.kintai.service.usecase.IAttendanceRegisterService;
import com.kintai.service.dto.request.RequestAttendanceRegisterDto;
import com.kintai.service.dto.response.ResponseAttendanceRegisterDto;
import com.kintai.service.usecase.impl.expansion.register.AttendanceRegisterServiceV1;
import lombok.extern.slf4j.Slf4j;

/**
 * 勤怠登録API
 */
@Slf4j
public class AttendanceRegisterApi {
    // 勤怠登録ユースケースサービス
    private final IAttendanceRegisterService iAttendanceRegisterService;

    /**
     * コンストラクタ
     */
    public AttendanceRegisterApi() {
        this.iAttendanceRegisterService = new AttendanceRegisterServiceV1(new CsvAttendanceSaveRepository(), new AttendanceFactory());
    }

    /**
     * 勤怠登録メソッド
     * @param args 入力値　各値は以下の通り
     *             args[0]：勤怠日(yyyyMMdd)
     *             args[1]：始業時刻(HHmm)
     *             args[2]：終業時刻(HHmm)
     *             args[3]：DB登録パスワード(アプリ実行時の曜日※を入力)
     */
    public void register(String[] args) {
        log.info("勤怠入力処理を開始します。");
        RequestAttendanceRegisterDto requestAttendanceRegisterDto = makeRequestAttendanceRegisterDto(args);
        // 入力値をログ出力
        log.info(requestAttendanceRegisterDto.toString());

        // 勤怠登録実施
        ResponseAttendanceRegisterDto responseAttendanceRegisterDto = iAttendanceRegisterService.register(requestAttendanceRegisterDto);

        // 出力値をログ出力
        log.info(responseAttendanceRegisterDto.toString());
        log.info("勤怠入力処理を終了します。");
    }

    /**
     * ユースケースに連携するリクエストDTOの生成
     * @param args 入力値
     * @return リクエストDTO
     */
    protected RequestAttendanceRegisterDto makeRequestAttendanceRegisterDto(String[] args) {
        return new RequestAttendanceRegisterDto(args[0], args[1], args[2], args[3]);
    }
}
