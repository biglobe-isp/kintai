package jp.co.biglobe.lib.publication.valueobject;

import java.lang.reflect.Field;

/**
 * value値を持つオブジェクト
 * <p>
 * 内部的にStringやInteger、LocalDateTimeなどの値を持つ
 *
 * @param <T> value値の型を指定する
 */
@SuppressWarnings("ALL")
interface HasValue<T> {

    /**
     * 内部的な値を返却
     * <p>
     * 本インタフェースを実装したオブジェクト内の変数名は "value" で統一する。
     * 変数名が "value" の場合、リフレクションを使用し、Getterの実装なしにアクセス可能な
     * デフォルトメソッドを提供する。
     */
    public default T getValue() {
        try {
            Field field = this.getClass().getDeclaredField("value");
            field.setAccessible(true);
            //noinspection unchecked
            return (T) field.get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("バリューオブジェクトのフィールド名は「value」を指定してください");
        }
    }
}
