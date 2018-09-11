//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.naosim.dddwork.domain.word;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForDb;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import jp.co.biglobe.lib.publication.enumeration.valuetype.DbValue;

import java.io.IOException;

public enum MethodType implements EnumConvertForApi, EnumConvertForDb, JsonSerializable {
    INPUT(MethodType.MethodTypeApiValue.INPUT, MethodType.MethodTypeDbValue.INPUT),
    TOTAL(MethodType.MethodTypeApiValue.TOTAL, MethodType.MethodTypeDbValue.TOTAL);

    private final ApiValue apiValue;
    private final DbValue dbValue;

    public void serialize(JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeString(this.getApiValue());
    }

    public void serializeWithType(JsonGenerator jgen, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
        jgen.writeString(this.getApiValue());
    }

    public String getApiValue() {
        return this.apiValue.getNoRefactoringValue();
    }

    public String getDbValue() {
        return this.dbValue.getNoRefactoringValue();
    }

    public boolean isInput() {
        return this == INPUT;
    }

    public boolean isTotal() {
        return this == TOTAL;
    }

    private MethodType(ApiValue apiValue, DbValue dbValue) {
        this.apiValue = apiValue;
        this.dbValue = dbValue;
    }

    private static enum MethodTypeDbValue implements DbValue {
        INPUT("input"),
        TOTAL("total");

        private final String noRefactoringValue;

        private MethodTypeDbValue(String noRefactoringValue) {
            this.noRefactoringValue = noRefactoringValue;
        }

        public String getNoRefactoringValue() {
            return this.noRefactoringValue;
        }
    }

    private static enum MethodTypeApiValue implements ApiValue {
        INPUT("input"),
        TOTAL("total");

        private final String noRefactoringValue;

        private MethodTypeApiValue(String noRefactoringValue) {
            this.noRefactoringValue = noRefactoringValue;
        }

        public String getNoRefactoringValue() {
            return this.noRefactoringValue;
        }
    }
}
