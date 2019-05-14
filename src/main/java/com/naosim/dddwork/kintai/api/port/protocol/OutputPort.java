package com.naosim.dddwork.kintai.api.port.protocol;


/**
 * ［出力ポート］規定
 */
public interface OutputPort<T> {

    void show(T model);
}
