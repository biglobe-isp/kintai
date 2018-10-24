package refoctor.domain.japan;

public class TotalWorkMinutesCalc {

    public void total(DateDomain dateDomain, TotalWorkMinutesRepository totalWorkMinutesRepository) {

        totalWorkMinutesRepository.totalOutPut(dateDomain);
    }
}
