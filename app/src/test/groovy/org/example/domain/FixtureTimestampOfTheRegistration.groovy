package org.example.domain

import java.time.LocalDateTime

class FixtureTimestampOfTheRegistration {
    static TimestampOfTheRegistration get(){
        new TimestampOfTheRegistration(LocalDateTime.of(2023, 2, 5, 10, 0))
    }
}
