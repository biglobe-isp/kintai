package com.naosim.dddwork.kintai.domain.core.type.time.component;

import com.naosim.dddwork.kintai.domain.core.type.time.amount.AmountOfMinutes;
import com.naosim.dddwork.kintai.domain.core.type.time.component.protocol.ClockTimeQuantifiable;
import com.naosim.dddwork.kintai.shared.easy.Validatable;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;


/**
 * 時（時刻構成要素）
 */
@EqualsAndHashCode
@ToString
public class HourOfDay implements Validatable, ClockTimeQuantifiable {

    static final int MIN = 0;
    static final int MAX = 24 + 9;      //TODO: TimeSystemで定義されている中から最大値をもらう

    @Range(min=MIN, max=MAX)
    final int value;


    /* 生成 */

    private HourOfDay(int value) {

        this.value = value;
        validate(this);
    }

    public static HourOfDay min() {
        return new HourOfDay(MIN);
    }

    public static HourOfDay max() {
        return new HourOfDay(MAX);
    }

    public static HourOfDay of(int value) {
        return new HourOfDay(value);
    }

    public HourOfDay copy() {
        return HourOfDay.of(value);
    }


    /* 時間量 */

    private AmountOfMinutes _minutesFromMidnight() {
        return AmountOfMinutes.of(value * 60);
    }

    @Override
    public AmountOfMinutes quantityOfMinutes() {
        return _minutesFromMidnight();
    }


    /* 時 変換 */

    /**
     * 正規化した［時］値を返す．
     * <pre>
     *     時は最大 {@link #MAX} を許容する。
     *     正規化した値とは、例えば 25時 → 1時 の意味。
     * </pre>
     *
     * @return 正規化した［時］値
     */
    int normalizedValue() {
        return value > 23 ? value - 24 : value;
    }

    /**
     * 指定された日境界時刻に適合するように変換した新しいインスタンスを返す．
     * <pre>
     *     例えば、この時値が2(時)で、指定された dayBoundaryHour が9(時)の場合は、26(時)。
     * </pre>
     *
     * @param dayBoundaryHour 日境界時刻（時）
     *
     * @return 時（時刻構成要素）
     */
    public HourOfDay convertedToFitDayBoundary(HourOfDay dayBoundaryHour) {

        final int boundaryHourValue = dayBoundaryHour.value;
        return HourOfDay.of(value <= boundaryHourValue ? value + 24 : value);
    }
}
