package jp.co.biglobe.lib.publication.valueobject;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static jp.co.biglobe.lib.publication.Not.not;

@SuppressWarnings("ALL")
public interface IsExist<S> {
    boolean isExist();

    default boolean isNotExist() {
        return not(isExist());
    }

    default <T extends S> T getIfExistsOrThrow(Supplier<? extends RuntimeException> throwAction) {
        return (T) Optional.of(this).filter(v -> v.isExist()).orElseThrow(throwAction);
    }

    default void ifExistThen(Runnable r) {
        if (isNotExist()) {
            return;
        }
        r.run();
    }

    default <T extends S> T get() {
        return (T) Optional.of(this).filter(v -> v.isExist()).get();
    }

    default <T extends S> Optional<T> castIfExist(Class<T> clazz) {
        return Optional.of(this).filter(v -> v.isExist()).map(v -> (T) v);
    }

    public static <I extends IsExist> Optional<I> findFirstExist(Supplier<I>... methods) {
        return Stream.of(methods).map(Supplier::get).filter(IsExist::isExist).findFirst();
    }
}