package org.example.domain

class FixtureWorkInformation {
    static WorkInformation get(){
        new WorkInformation(
                FixtureWorkTime.get(),
                FixtureTotalHours.get(),
                FixtureDateToRegister.get(),
                FixtureTimestampOfTheRegistration.get()
        )
    }
}
