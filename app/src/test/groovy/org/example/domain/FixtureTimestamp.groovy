package org.example.domain

import java.time.LocalDateTime

class FixtureTimestamp {
    static Timestamp get() {
        Timestamp.of(LocalDateTime.of(2023, 2, 5, 10, 0).toString())
    }
}
