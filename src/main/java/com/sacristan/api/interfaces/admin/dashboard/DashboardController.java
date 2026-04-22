package com.sacristan.api.interfaces.admin.dashboard;

import com.sacristan.api.interfaces.admin.dashboard.dtos.DailyExpectedSequencesDto;
import com.sacristan.api.interfaces.admin.dashboard.dtos.LatestReproductionsDto;
import com.sacristan.api.interfaces.admin.dashboard.dtos.MostUsedSequencesDto;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/statistics")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/total-sequences")
    public ResponseEntity<Long> countTotalSequences() {
        return ResponseEntity.ok(dashboardService.countTotalSequences());
    }

    @GetMapping("/total-students")
    public ResponseEntity<Long> countTotalStudents() {
        return ResponseEntity.ok(dashboardService.countTotalStudents());
    }

    @GetMapping("/total-teachers")
    public ResponseEntity<Long> countTotalTeachers() {
        return ResponseEntity.ok(dashboardService.countTotalTeachers());
    }

    @GetMapping("/completed-sequences")
    public ResponseEntity<Long> countCompletedSequencesToday() {
        return ResponseEntity.ok(dashboardService.countCompletedSequencesToday());
    }

    @GetMapping("/average-completed-sequences")
    public ResponseEntity<Double> averageCompletedSequencesPerStudentToday() {
        return ResponseEntity.ok(dashboardService.averageCompletedSequencesPerStudentToday());
    }

    @GetMapping("/daily-sequences")
    public ResponseEntity<@Nullable List<DailyExpectedSequencesDto>> getDailyExpectedAndCompleted(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ) {
        return ResponseEntity.ok(dashboardService.getDailyExpectedAndCompleted(from, to));
    }

    @GetMapping("/most-used-sequences")
    public ResponseEntity<@Nullable Page<MostUsedSequencesDto>> getMostUsedSequences(
            @PageableDefault(size = 5) Pageable pageable
    ) {
        return ResponseEntity.ok(dashboardService.getMostUsedSequences(pageable));
    }

    @GetMapping("/latest-reproductions")
    public ResponseEntity<@Nullable Page<LatestReproductionsDto>> getLatestReproductions(
            @PageableDefault(size = 5) Pageable pageable
    ) {
        return ResponseEntity.ok(dashboardService.getLatestReproductions(pageable));
    }
}

