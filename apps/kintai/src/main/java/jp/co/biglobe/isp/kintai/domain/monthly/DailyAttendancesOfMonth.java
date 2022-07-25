package jp.co.biglobe.isp.kintai.domain.monthly;

import jp.co.biglobe.isp.kintai.domain.daily.DailyAttendance;

import java.util.List;

public record DailyAttendancesOfMonth(List<DailyAttendance> attendances) {
    public TotalWorkedHoursResult totalWorkedHours() {
        return attendances.stream()
                .collect(
                        TotalWorkedHoursAccumulator::new,
                        TotalWorkedHoursAccumulator::add,
                        TotalWorkedHoursAccumulator::merge
                ).result();
    }

    private static class TotalWorkedHoursAccumulator {
        private int totalWorkTimeMinutes;
        private int totalOvertimeMinutes;

        public void add(DailyAttendance daily) {
            totalWorkTimeMinutes += daily.workTimeMinutes().value();
            totalOvertimeMinutes += daily.overtimeMinutes().value();
        }

        public TotalWorkedHoursResult result() {
            return new TotalWorkedHoursResult(
                    totalWorkTimeMinutes,
                    totalOvertimeMinutes
            );
        }

        void merge(TotalWorkedHoursAccumulator other) {
            totalWorkTimeMinutes += other.totalWorkTimeMinutes;
            totalOvertimeMinutes += other.totalOvertimeMinutes;
        }
    }
}


