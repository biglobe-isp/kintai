package org.example.domain

class FixtureStartTime {
    static StartTime get() {
        StartTime.of(FixtureStartHour.get(), FixtureStartMinute.get());
    }
}