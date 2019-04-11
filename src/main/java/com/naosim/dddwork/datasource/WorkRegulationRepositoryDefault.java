package com.naosim.dddwork.datasource;

import com.naosim.dddwork.domain.WorkRegulation;
import com.naosim.dddwork.domain.WorkRegulationRepository;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Repository
public class WorkRegulationRepositoryDefault implements WorkRegulationRepository {

    @Override
    public WorkRegulation fetchDefault() {
        // TODO: Not Implemented
        throw new NotImplementedException();
    }
}
