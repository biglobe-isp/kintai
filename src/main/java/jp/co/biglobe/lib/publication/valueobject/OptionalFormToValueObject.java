package jp.co.biglobe.lib.publication.valueobject;

import jp.co.biglobe.lib.publication.date.DateParser;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Optional;

@SuppressWarnings("ALL")
public interface OptionalFormToValueObject<T> {

    default String init(String value) {
        if ("".equals(value)) return null;
        else return value;
    }

    public String getValue();

    Optional<T> getValueObject();

    default Optional<T> toValueObject(Class<T> clazz) {

        if (getValue() == null) {
            return Optional.empty();
        }

        try {
            Field field = clazz.getDeclaredField("value");

            if (field.getType() == (Class<?>) String.class) {
                return Optional.of(clazz.getConstructor(field.getType()).newInstance(getValue()));
            }

            if (field.getType() == (Class<?>) LocalDate.class) {
                return Optional.of(clazz.getConstructor(field.getType()).newInstance(DateParser.parse_yyyyMMdd(getValue())));
            }

            if (field.getType() == (Class<?>) YearMonth.class) {
                return Optional.of(clazz.getConstructor(field.getType()).newInstance(DateParser.parse_yyyyMM(getValue())));
            }

        } catch (Exception ignored) {
        }

        return Optional.empty();
    }

}
