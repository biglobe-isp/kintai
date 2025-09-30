package org.example.domain

class FixtureTargetDate {
    static TargetDate get() {
        new TargetDate(FixtureDate.get());
    }
}
