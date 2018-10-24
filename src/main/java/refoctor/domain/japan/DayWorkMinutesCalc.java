package refoctor.domain.japan;

public class DayWorkMinutesCalc {

    public void input(DateDomain dateDomain, StartTime startTime, EndTime endTime, WorkTime workTime, DayWorkMinutesRepository dayWorkMinutesRepository) {

        dayWorkMinutesRepository.dayInPut(dateDomain, startTime, endTime, workTime);

    }
}
