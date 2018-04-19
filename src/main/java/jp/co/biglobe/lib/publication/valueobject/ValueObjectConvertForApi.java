package jp.co.biglobe.lib.publication.valueobject;

@SuppressWarnings("ALL")
public interface ValueObjectConvertForApi<T> extends HasValue<T> {
    /**
     * バリューオブジェクトのAPI値を返却する
     */
    public default String getApiValue() {
        T value = getValue();
        return StringConverter.convert(value);
    }
}
