package jp.co.biglobe.lib.publication.valueobject;

import jp.co.biglobe.lib.publication.date.DateFormatter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.YearMonth;
import java.util.Optional;

@SuppressWarnings("ALL")
final class StringConverter {

    /**
     * 文字列、数値、日付を文字列型に変換する
     * <p>
     * nullの場合は、そのままnullを返却する
     * 変換不可能なオブジェクトが指定された場合、例外をスローする
     */
    static <T> String convert(final T value) {

        // 実はこのif文はなくても良いが、nullの時の挙動を明示的にしたいのであえて記述している
        if (value == null) {
            return null;
        }

        // 文字列＆数値関連
        if (value instanceof String) {
            return value.toString();
        }

        if (value instanceof Number) {
            return value.toString();
        }

        // 日付関連
        if (value instanceof LocalDateTime) {
            return DateFormatter.format_yyyyMMddHHmmss((LocalDateTime) value);
        }

        if (value instanceof LocalDate) {
            return DateFormatter.format_yyyyMMdd((LocalDate) value);
        }

        if (value instanceof LocalTime) {
            return DateFormatter.format_HHmmss((LocalTime) value);
        }

        if (value instanceof YearMonth) {
            return DateFormatter.format_yyyyMM((YearMonth) value);
        }

        if (value instanceof MonthDay) {
            return DateFormatter.format_MMdd((MonthDay) value);
        }

        throw new RuntimeException(value.getClass().getSimpleName() + "型はAPI値に変換できません");
    }

    private StringConverter() {
    }

    private static Object getOptionalValue(Optional value) {
        if (!value.isPresent()) {
            return null;
        }
        return value.get();
    }
}
