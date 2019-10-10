import com.naosim.dddwork.domain.KintaiData
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class KintaiCalcuratorTest extends Specification {

    @Unroll
    def "CalcurateJiturodo"() {
        DateTimeFormatter formatYYYYMMDD = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter formatMMHH = DateTimeFormatter.ofPattern("HHmm");
        def date = LocalDate.parse(dateStr, formatYYYYMMDD);
        def start = LocalTime.parse(startStr, formatMMHH);
        def end = LocalTime.parse(endStr, formatMMHH);

        def kintai = new KintaiData(date, start, end);
        expect:
        kintai.getWorkMinutes() == result;
        where:
        dateStr    | startStr | endStr || result
        "20191002" | "0900"   | "1000" || "60"
        "20191002" | "0900"   | "1200" || "180"
        "20191002" | "0900"   | "1201" || "180"
        "20191003" | "0900"   | "1800" || "480"
    }

    @Unroll
    def "CalcurateZangyo"() {
        DateTimeFormatter formatYYYYMMDD = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter formatMMHH = DateTimeFormatter.ofPattern("HHmm");
        def date = LocalDate.parse(dateStr, formatYYYYMMDD);
        def start = LocalTime.parse(startStr, formatMMHH);
        def end = LocalTime.parse(endStr, formatMMHH);

        def kintai = new KintaiData(date, start, end);
        expect:
        kintai.getOverWorkMinutes() == result;
        where:
        dateStr    | startStr | endStr || result
        "20191002" | "0900"   | "1800" || "0"
        "20191003" | "0900"   | "2000" || "60"
    }
}
