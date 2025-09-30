package org.example.domain

class FixtureCreateTimestamp {
    static CreateTimestamp get(){
        new CreateTimestamp(FixtureTimestamp.get())
    }
}
