package org.example.domain

class FixtureWorkTime {
    static WorkTime get() {
        WorkTime.of(FixtureWorkStartTime.get(), FixtureWorkEndTime.get())
    }
}
