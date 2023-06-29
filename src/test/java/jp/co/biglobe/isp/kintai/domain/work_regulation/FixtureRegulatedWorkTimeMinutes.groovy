package jp.co.biglobe.isp.kintai.domain.work_regulation

class FixtureRegulatedWorkTimeMinutes {
    static int get(){
        WorkRegulation workRegulation = FixtureWorkRegulation.get()
        workRegulation.getRegulatedWorkMinutes()
    }
}
