package refactor.domain;

public interface Time {
    int minutes();

    boolean isLaterThanOrEqual(Time other);
}
