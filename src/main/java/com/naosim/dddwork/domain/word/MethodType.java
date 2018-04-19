package com.naosim.dddwork.domain.word;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForDb;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import jp.co.biglobe.lib.publication.enumeration.valuetype.DbValue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.IOException;

/**
 * 処理区分
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum MethodType implements EnumConvertForApi, EnumConvertForDb, JsonSerializable {
    INPUT(MethodTypeApiValue.INPUT, MethodTypeDbValue.INPUT),
    TOTAL(MethodTypeApiValue.TOTAL, MethodTypeDbValue.TOTAL),;

    @Override
    public void serialize(JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeString(getApiValue());
    }

    @Override
    public void serializeWithType(JsonGenerator jgen, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
        jgen.writeString(getApiValue());
    }

    private final ApiValue apiValue;

    public String getApiValue() {
        return apiValue.getNoRefactoringValue();
    }

    private final DbValue dbValue;

    public String getDbValue() {
        return dbValue.getNoRefactoringValue();
    }

    @AllArgsConstructor
    private enum MethodTypeApiValue implements ApiValue {
        INPUT("input"),
        TOTAL("total"),;
        @Getter
        private final String noRefactoringValue;
    }

    @AllArgsConstructor
    private enum MethodTypeDbValue implements DbValue {
        INPUT("input"),
        TOTAL("total"),;
        @Getter
        private final String noRefactoringValue;
    }

    // INPUTかどうかの判定
    public boolean isInput() {
        return this == MethodType.INPUT;
    }

    // TOTALかどうかの判定
    public boolean isTotal() {
        return this == MethodType.TOTAL;
    }
}
