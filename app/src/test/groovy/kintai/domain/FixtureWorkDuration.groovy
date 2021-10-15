package kintai.domain

import java.time.Duration

class FixtureWorkDuration {
    static WorkDuration getFullTime() {
        new WorkDuration(Duration.ofHours(8))
    }

    static WorkDuration getShortTime() {
        new WorkDuration(Duration.ofHours(3))
    }

    static WorkDuration getOverTime() {
        new WorkDuration(Duration.ofHours(11))
    }

}
