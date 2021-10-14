package kintai.domain

class FixtureOverWorkDuration {
    static OverWorkDuration get() {
        return new OverWorkDuration(FixtureWorkDuration.getFullTime())
    }

}
