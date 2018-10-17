package refoctor.domain;

public class TotalWorkMinutesCalc {

    public void total(TotalWorkMinutesRepository totalWorkMinutesRepository, ArgsList argsList) {
        String yearMonth = argsList.getDate();
        totalWorkMinutesRepository.totalOutPut(yearMonth);
    }
}
