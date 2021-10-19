package kintai.domain

import java.time.Duration

class FixtureOverWorkDuration {
    static OverWorkDuration get() {
        return new OverWorkDuration(Duration.ofHours(0))
    }

}
