package org.example.domain

class FixtureTotalHours {
    static TotalHours get(){
        TotalHours.of(FixtureWorkTime.get())
    }
}
