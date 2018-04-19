package jp.co.biglobe.lib.publication.typehandler;

import jp.co.biglobe.lib.publication.db.EnumDbValueConverter;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForDb;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 既存テーブルからデータ取得時、enum型に変換したい場合に使用するTypeHandler。
 * UTF-8対応版
 */
@SuppressWarnings("ALL")
@MappedTypes(EnumConvertForDb.class)
public final class EnumTypeHandlerWithUTF8<E extends Enum<E> & EnumConvertForDb> extends BaseTypeHandler<E> {

    private Class<E> type;

    public EnumTypeHandlerWithUTF8(Class<E> type) {
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E e, JdbcType jdbcType) throws SQLException {
        String utf8String = e.getDbValue();
        if (utf8String == null) utf8String = "";
        ps.setString(i, utf8String);
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        if (value == null) return null;
        return EnumDbValueConverter.convert(type, value);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        if (value == null) return null;
        return EnumDbValueConverter.convert(type, value);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        if (value == null) return null;
        return EnumDbValueConverter.convert(type, value);
    }

}
