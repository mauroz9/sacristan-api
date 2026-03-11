package com.sacristan.api.interfaces.admin.dashboard.services;

import com.sacristan.api.global.models.Status;
import com.sacristan.api.global.repositories.ReproductionRepository;
import com.sacristan.api.global.repositories.SequenceRepository;
import com.sacristan.api.global.repositories.StudentRepository;
import com.sacristan.api.global.repositories.TeacherRepository;
import com.sacristan.api.interfaces.admin.dashboard.dtos.DailyExpectedSequencesDto;
import com.sacristan.api.interfaces.admin.dashboard.dtos.LatestReproductionsDto;
import com.sacristan.api.interfaces.admin.dashboard.dtos.MostUsedSequencesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final SequenceRepository sequenceRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final ReproductionRepository reproductionRepository;

    public long countTotalSequences() {
        return sequenceRepository.count();
    }

    public long countTotalStudents() {
        return studentRepository.count();
    }

    public long countTotalTeachers() {
        return teacherRepository.count();
    }

    public long countCompletedSequencesToday() {
        LocalDateTime today = LocalDate.now().atStartOfDay();
        LocalDateTime tomorrow = today.plusDays(1);

        return reproductionRepository.countCompletedToday(today, tomorrow, Status.COMPLETED);
    }

    public double averageCompletedSequencesPerStudentToday() {
        LocalDateTime today = LocalDate.now().atStartOfDay();
        LocalDateTime tomorrow = today.plusDays(1);

        return reproductionRepository.averageCompletedPerStudentToday(today, tomorrow, Status.COMPLETED);
    }

    public List<DailyExpectedSequencesDto> getDailyExpectedAndCompleted(LocalDate from, LocalDate to) {
        if (from.isAfter(to)) {
            throw new IllegalArgumentException("'from' date must be on or before 'to' date");
        }

        List<DailyExpectedSequencesDto> result = new ArrayList<>();
        LocalDate current = from;

        while (!current.isAfter(to)) {
            LocalDateTime dayStart = current.atStartOfDay();
            LocalDateTime dayEnd = dayStart.plusDays(1);

            long expected = sequenceRepository.countExpectedSequencesForDay(current.getDayOfWeek().name());
            long completed = sequenceRepository.countCompletedSequencesForDay(
                    dayStart,
                    dayEnd,
                    Status.COMPLETED.name()
            );

            result.add(new DailyExpectedSequencesDto(current, expected, completed));
            current = current.plusDays(1);
        }

        return result;
    }

    public Page<MostUsedSequencesDto> getMostUsedSequences(Pageable pageable) {
        return sequenceRepository.findMostUsedSequences(pageable);
    }

    public Page<LatestReproductionsDto> getLatestReproductions(Pageable pageable) {
        return reproductionRepository.findLatestReproductions(pageable);
    }

}


