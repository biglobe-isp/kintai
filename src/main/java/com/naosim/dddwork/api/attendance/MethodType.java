package com.naosim.dddwork.api.attendance;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * メソッドタイプ
 * 2018/09/27 新規作成
 * 2018/09/28 レビュー指摘事項反映 パッケージをdomain→apiに移動
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
