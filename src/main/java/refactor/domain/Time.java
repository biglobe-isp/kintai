package refactor.domain;

import lombok.NonNull;

public interface Time {
    int minutes();

    boolean isLaterThanOrEqual(@NonNull Time other);
}
