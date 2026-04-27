package com.sacristan.api.global.entities.assignments.routineSegment;

import com.sacristan.api.global.services.BaseServiceImpl;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoutineSegmentModelService extends BaseServiceImpl<RoutineSegment, Long, RoutineSegmentRepository> {

    public void deleteByRoutineId(Long id) {
        repository.deleteByRoutineId(id);
    }

    public List<RoutineSegment> getByRoutineId(Long routineId) {
        return repository.findByRoutineId(routineId);
    }
}

