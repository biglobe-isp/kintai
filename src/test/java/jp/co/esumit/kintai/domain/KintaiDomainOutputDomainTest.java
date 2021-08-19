package jp.co.esumit.kintai.domain;

import jp.co.esumit.kintai.domain.kintai_info.KintaiInfo;
import jp.co.esumit.kintai.domain.kintai_info.office_minutes.OfficeMinutes;
import jp.co.esumit.kintai.domain.kintai_info.overtime_minutes.OvertimeMinutes;
import jp.co.esumit.kintai.domain.summary.MonthlySummary;
import jp.co.esumit.kintai.domain.summary.total_office_minutes.TotalOfficeMinutes;
import jp.co.esumit.kintai.domain.summary.total_overtime_minutes.TotalOvertimeMinutes;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class KintaiDomainOutputDomainTest {
    @Test
    public void n001() {


        List<KintaiInfo> inputCsvColsList = this.getCsvColumns(5);

        TotalOfficeMinutes expOfficeMins = new TotalOfficeMinutes(540 * 5);
        TotalOvertimeMinutes expOvertime = new TotalOvertimeMinutes(60 * 5);

        MonthlySummary expMonthlySummary = new MonthlySummary(expOfficeMins, expOvertime);

        this.executeTest(inputCsvColsList, expMonthlySummary, null);
    }

    private void executeTest(
            List<KintaiInfo> inputCsvColsList,
            MonthlySummary expMonthlySummary,
            Exception expException) {

        //KintaiDomain testClass = new KintaiDomain();

        Exception actException = null;
        MonthlySummary actMonthlySummary = null;

        try {
            //  actMonthlySummary = testClass.outputDomain(inputCsvColsList);
        } catch (Exception e) {
            actException = e;
        }

        assertEquals("MonthlySummary", expMonthlySummary, actMonthlySummary);
        assertEquals("Exception", expException, actException);
    }

    private List<KintaiInfo> getCsvColumns(int csvCount) {

        List<KintaiInfo> kintaiInfoList = new ArrayList<>();


        for (int i = 0; i < csvCount; i++) {

            OfficeMinutes of = new OfficeMinutes(540);
            OvertimeMinutes ov = new OvertimeMinutes(60);
            KintaiInfo csvCol = new KintaiInfo(null, null, null, of, ov, null);
            kintaiInfoList.add(csvCol);
        }

        return kintaiInfoList;
    }
}
