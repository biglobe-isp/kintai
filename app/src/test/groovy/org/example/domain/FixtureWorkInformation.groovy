package org.example.domain

class FixtureWorkInformation {
    static WorkInformation get(){
        new WorkInformation(
                FixtureWorkTime.get(),
                FixtureDateToRegister.get(),
                FixtureTimestampOfTheRegistration.get()
        )
    }
}
