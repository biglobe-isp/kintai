package org.example.domain

class FixtureWorkInformation {
    static WorkInformation get(){
        return  new WorkInformation(
                FixtureWorkTime.get(),
                FixtureTargetDate.get(),
                FixtureCreateTimestamp.get()
        )
    }
}
