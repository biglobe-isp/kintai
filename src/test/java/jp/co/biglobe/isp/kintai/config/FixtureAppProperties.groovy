package jp.co.biglobe.isp.kintai.config

class FixtureAppProperties {
    static AppProperties get() {
        def appProperties = new AppProperties()
        AppProperties.metaClass.setAttribute(appProperties, "workRecordCsv", "src/test/resources/test_work_record.csv")
        return appProperties
    }
}
