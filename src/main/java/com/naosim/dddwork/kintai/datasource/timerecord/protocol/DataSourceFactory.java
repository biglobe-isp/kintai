package com.naosim.dddwork.kintai.datasource.timerecord.protocol;

import com.naosim.dddwork.kintai.domain.repository.protocol.WorkedTimeRepository;


/**
 * データソースファクトリ規定
 */
public interface DataSourceFactory {

    WorkedTimeRepository workedTimeRepository();

    // ここに追加していく
}
