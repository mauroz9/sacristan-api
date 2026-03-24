package com.sacristan.api.global.entities.tracking.reproduction;

import com.sacristan.api.global.entities.tracking.status.Status;
import com.sacristan.api.global.services.BaseServiceImpl;
import com.sacristan.api.interfaces.admin.dashboard.dtos.LatestReproductionsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReproductionModelService extends BaseServiceImpl<Reproduction, Long, ReproductionRepository> {

    public long countCompletedToday(LocalDateTime today, LocalDateTime tomorrow, Status status) {
        return repository.countCompletedToday(today, tomorrow, status);
    }

    public double averageCompletedPerStudentToday(LocalDateTime today, LocalDateTime tomorrow, Status status) {
        return repository.averageCompletedPerStudentToday(today, tomorrow, status);
    }

    public Page<LatestReproductionsDto> findLatestReproductions(Pageable pageable, Status status) {
        return repository.findLatestReproductions(pageable, status);
    }
}



