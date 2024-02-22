package com.kintai.service.dto.request;

import lombok.Getter;

/**
 * 勤怠データを取得するために必要なデータを格納するリクエストDTO
 * APIからユースケース宛にデータを連携する際に使用します。
 */
public class RequestAttendanceGetDto {
    // パスワード
    @Getter
    private String password;

    /**
     * コンストラクタ
     * @param password パスワード
     */
    public RequestAttendanceGetDto(String password) {
        this.password = password;
    }
}
