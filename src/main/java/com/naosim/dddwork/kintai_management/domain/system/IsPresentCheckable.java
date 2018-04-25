package com.naosim.dddwork.kintai_management.domain.system;

/**
 * isPresentメソッドを提供するインタフェース
 */
public interface IsPresentCheckable {

    Object getValue();

    default boolean isPresent() {
        return getValue() != null;
    }

}
