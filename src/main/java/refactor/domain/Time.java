package refactor.domain;

import lombok.NonNull;

public interface Time {
    int getMinutes();

    boolean isLaterThanOrEqual(@NonNull Time other);
}
