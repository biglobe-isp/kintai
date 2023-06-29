package jp.co.biglobe.isp.kintai.domain.work_regulation

import jp.co.biglobe.isp.kintai.datasource.WorkRegulationSetting

class FixtureWorkRegulation {
    static WorkRegulation get() {
        new WorkRegulationSetting().refer()
    }
}
