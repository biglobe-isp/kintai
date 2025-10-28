package org.example.domain

class FixtureWorkTime {
    static WorkTime get() {
        new WorkTime(FixtureWorkStartTime.get(), FixtureWorkEndTime.get())
    }
}
