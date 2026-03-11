package com.sacristan.api.interfaces.admin.dashboard.controllers;

import com.sacristan.api.interfaces.admin.dashboard.dtos.DailyExpectedSequencesDto;
import com.sacristan.api.interfaces.admin.dashboard.dtos.LatestReproductionsDto;
import com.sacristan.api.interfaces.admin.dashboard.dtos.MostUsedSequencesDto;
import com.sacristan.api.interfaces.admin.dashboard.services.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
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
    public long countTotalSequences() {
        return dashboardService.countTotalSequences();
    }

    @GetMapping("/total-students")
    public long countTotalStudents() {
        return dashboardService.countTotalStudents();
    }

    @GetMapping("/total-teachers")
    public long countTotalTeachers() {
        return dashboardService.countTotalTeachers();
    }

    @GetMapping("/completed-sequences")
    public long countCompletedSequencesToday() {
        return dashboardService.countCompletedSequencesToday();
    }

    @GetMapping("/daily-sequences")
    public List<DailyExpectedSequencesDto> getDailyExpectedAndCompleted(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ) {
        return dashboardService.getDailyExpectedAndCompleted(from, to);
    }

    @GetMapping("/most-used-sequences")
    public Page<MostUsedSequencesDto> getMostUsedSequences(
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return dashboardService.getMostUsedSequences(pageable);
    }

    @GetMapping("/latest-reproductions")
    public Page<LatestReproductionsDto> getLatestReproductions(
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return dashboardService.getLatestReproductions(pageable);
    }

}
