package refactor.domain;

public class OverWorkMinutes {
    public int calculateOverWorkMinutes(int workMinutes) {
        return Math.max(workMinutes - 8 * 60, 0);
    }
}
