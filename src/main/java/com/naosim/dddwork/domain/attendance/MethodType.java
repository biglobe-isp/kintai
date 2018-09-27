package com.naosim.dddwork.domain.attendance;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * メソッドタイプ
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class MethodType {

    @Getter
    private final String value;

    public boolean isInput() {
        return "input".equals(this.getValue());
    }

    public boolean isTotal() {
        return "total".equals(this.getValue());
    }
}
