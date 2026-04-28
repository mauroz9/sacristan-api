package com.sacristan.api.global.entities.tracking.reproduction;

import com.sacristan.api.global.entities.tracking.status.Status;
import com.sacristan.api.global.services.BaseServiceImpl;
import com.sacristan.api.interfaces.admin.dashboard.dtos.LatestReproductionsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    public List<Reproduction> findCompletedRoutineSequenceForToday(Long userId, Long routineSegmentId, LocalDateTime today, LocalDateTime tomorrow, Status completed) {
        return repository.findCompletedRoutineSegmentForToday(userId, routineSegmentId, today, tomorrow, completed);
    }

    public void deleteByRoutineSegmentId(Long id) {
        repository.deleteByRoutineSegmentId(id);
    }

    public void deleteByStudentId(Long id) {
        repository.deleteByStudentId(id);
    }

    public Integer countByStudentIdAndStatus(Long studentId, Status status) {
        return repository.countByStudentIdAndStatus(studentId, status);
    }

    public Reproduction findFirstByStudentIdOrderByStartedAtDesc(Long studentId) {
        return repository.findFirstByStudentIdOrderByStartedAtDesc(studentId);
    }

    public Page<Reproduction> findByStudentIdOrderByStartedAtDesc(Long studentId, Pageable pageable) {
        return repository.findByStudentIdOrderByStartedAtDesc(studentId, pageable);
    }

    public List<Reproduction> findByStudentIdAndStartedAtAfter(Long studentId, LocalDateTime date) {
        return repository.findByStudentIdAndStartedAtAfter(studentId, date);
    }

    public List<java.sql.Date> findDistinctReproductionDatesByStudentId(Long studentId) {
        return repository.findDistinctReproductionDatesByStudentId(studentId);
    }

    public List<Reproduction> findByStudentId(Long studentId) {
        return repository.findByStudentId(studentId);
    }

    public boolean existsCompletedToday(Long studentId, Long segmentId, LocalDate today) {
        return repository.existsCompletedToday(studentId, segmentId, Status.COMPLETED, today);
    }
}
