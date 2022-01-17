package domain.value;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class WorkRecordId {
    private static long seed = 0;

    private final long workRecordId;

    WorkRecordId() {
        this.workRecordId = seed++;
    }
}
