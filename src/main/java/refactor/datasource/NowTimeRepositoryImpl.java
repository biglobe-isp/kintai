package refactor.datasource;

import refactor.domain.repository.NowTimeRepository;

import java.time.LocalDateTime;

public class NowTimeRepositoryImpl implements NowTimeRepository {
    public String getNowTime(){
        return LocalDateTime.now().toString();
    }
}
