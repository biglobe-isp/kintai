package jp.co.biglobe.lib.publication.form;

import jp.co.biglobe.lib.publication.date.DateParser;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@SuppressWarnings("ALL")
public interface OptionalToValueObject<T> {

    default String init(String value) {
        return "".equals(value) ? null : value;
    }

    public String getValue();

    public Optional<T> getValueObject();

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
                return Optional.of(
                        clazz.getConstructor(field.getType())
                                .newInstance(DateParser.parse_yyyyMMdd(getValue()))
                );
            }

            if (field.getType() == (Class<?>) LocalDateTime.class) {
                return Optional.of(
                        clazz.getConstructor(field.getType())
                                .newInstance(DateParser.parse_yyyyMMddHHmmss(getValue()))
                );
            }

        } catch (Exception ignored) {
        }

        return Optional.empty();
    }
}
