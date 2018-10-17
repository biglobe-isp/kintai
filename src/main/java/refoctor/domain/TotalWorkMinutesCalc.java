package refoctor.domain;

public class TotalWorkMinutesCalc {

    TotalWorkMinutesRepository totalWorkMinutesRepository;

    public void total(String[] args) {
        String yearMonth = args[1];
        totalWorkMinutesRepository.totalOutPut(yearMonth);
    }
}
