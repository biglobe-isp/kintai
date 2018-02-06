package jp.co.biglobe.test.util.reflection;

import java.lang.reflect.Field;

/**
 * privateな要素にアクセスするクラス
 *
 * ＜＜警告！＞＞
 * テストコード以外からは"絶対"に呼び出さないこと
 * このクラスを使うと、カプセル化が簡単に破壊されてしまう
 *
 * また、テストコードでも多用は避けること
 * このクラスが多用される場合、設計の改善を強く推奨する
 */
public class PrivateAccessorForTest {
    /**
     * private変数を取得する
     *
     * @param <T> private変数を保持しているオブジェクトの型
     * @param <O> メソッド返却値の型
     * @param object private変数を保持しているオブジェクト
     * @param privateFieldName private変数名
     * @return
     */
    public static <T, O> O getPrivateField(T object, String privateFieldName) throws Exception {
        Field field = object.getClass().getDeclaredField(privateFieldName);
        field.setAccessible(true);
        return (O) field.get(object);
    }
}
