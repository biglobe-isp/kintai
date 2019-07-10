package refactor.domain;

import java.time.LocalDateTime;

public class CurrentTime {
    public String now() {
        return LocalDateTime.now().toString();
    }
}
