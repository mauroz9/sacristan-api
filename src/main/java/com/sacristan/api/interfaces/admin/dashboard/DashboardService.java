package com.sacristan.api.interfaces.admin.dashboard;

import com.sacristan.api.global.entities.content.sequence.SequenceModelService;
import com.sacristan.api.global.entities.tracking.reproduction.ReproductionModelService;
import com.sacristan.api.global.entities.tracking.status.Status;
import com.sacristan.api.global.entities.users.student.StudentModelService;
import com.sacristan.api.global.entities.users.teacher.TeacherModelService;
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

    private final SequenceModelService sequenceModelService;
    private final StudentModelService studentModelService;
    private final TeacherModelService teacherModelService;
    private final ReproductionModelService reproductionModelService;

    public long countTotalSequences() {
        return sequenceModelService.count();
    }

    public long countTotalStudents() {
        return studentModelService.count();
    }

    public long countTotalTeachers() {
        return teacherModelService.count();
    }

    public long countCompletedSequencesToday() {
        LocalDateTime today = LocalDate.now().atStartOfDay();
        LocalDateTime tomorrow = today.plusDays(1);

        return reproductionModelService.countCompletedToday(today, tomorrow, Status.COMPLETED);
    }

    public double averageCompletedSequencesPerStudentToday() {
        LocalDateTime today = LocalDate.now().atStartOfDay();
        LocalDateTime tomorrow = today.plusDays(1);

        return reproductionModelService.averageCompletedPerStudentToday(today, tomorrow, Status.COMPLETED);
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

            long expected = sequenceModelService.countExpectedSequencesForDay(current.getDayOfWeek().name());
            long completed = sequenceModelService.countCompletedSequencesForDay(
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
        return sequenceModelService.findMostUsedSequences(pageable);
    }

    public Page<LatestReproductionsDto> getLatestReproductions(Pageable pageable) {
        return reproductionModelService.findLatestReproductions(pageable, Status.COMPLETED);
    }
}


