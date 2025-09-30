package org.example.domain

class FixtureEndTime {
    static EndTime get() {
        EndTime.of(FixtureEndHour.get(), FixtureEndMinute.get())
    }
}