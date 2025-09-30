package org.example.domain

class FixtureWorkTime {
    static WorkTime get() {
        WorkTime.of(FixtureStartTime.get(), FixtureEndTime.get())
    }
}
