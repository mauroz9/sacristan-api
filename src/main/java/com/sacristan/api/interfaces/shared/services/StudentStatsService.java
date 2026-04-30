package com.sacristan.api.interfaces.shared.services;

import com.sacristan.api.global.entities.assignments.routineSegment.RoutineSegment;
import com.sacristan.api.global.entities.content.weekDays.DaysOfTheWeek;
import com.sacristan.api.global.entities.tracking.reproduction.Reproduction;
import com.sacristan.api.global.entities.tracking.reproduction.ReproductionModelService;
import com.sacristan.api.global.entities.tracking.status.Status;
import com.sacristan.api.global.entities.users.student.Student;
import com.sacristan.api.global.entities.users.student.StudentModelService;
import com.sacristan.api.interfaces.admin.alumnos.dtos.response.dashboard.*;
import com.sacristan.api.interfaces.admin.alumnos.dtos.response.reproduction.ButtonClicksDTO;
import com.sacristan.api.interfaces.admin.alumnos.dtos.response.reproduction.ReproductionDetailDTO;
import com.sacristan.api.interfaces.admin.alumnos.dtos.response.reproduction.StepTrackingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentStatsService {

    private final StudentModelService studentModelService;
    private final ReproductionModelService reproductionModelService;

    // ==================== Utility Methods ====================

    public Integer calculateStreak(List<Date> dates) {
        if (dates == null || dates.isEmpty()) return 0;
        int racha = 1;

        LocalDate today = LocalDate.now();
        LocalDate currentCheck = dates.getFirst().toLocalDate();

        if (currentCheck.isBefore(today.minusDays(1))) return 0;

        for (int i = 1; i < dates.size(); i++) {
            LocalDate previousDate = dates.get(i).toLocalDate();
            if (currentCheck.minusDays(1).equals(previousDate)) {
                racha++;
                currentCheck = previousDate;
            } else {
                break;
            }
        }
        return racha;
    }

    public DaysOfTheWeek getTodayEnum() {
        return switch (LocalDate.now().getDayOfWeek()) {
            case MONDAY -> DaysOfTheWeek.MONDAY;
            case TUESDAY -> DaysOfTheWeek.TUESDAY;
            case WEDNESDAY -> DaysOfTheWeek.WEDNESDAY;
            case THURSDAY -> DaysOfTheWeek.THURSDAY;
            case FRIDAY -> DaysOfTheWeek.FRIDAY;
            case SATURDAY -> DaysOfTheWeek.SATURDAY;
            case SUNDAY -> DaysOfTheWeek.SUNDAY;
        };
    }

    public String calculateElapsedTime(LocalDateTime time) {
        if (time == null) return "Sin actividad";
        Duration duration = Duration.between(time, LocalDateTime.now());
        if (duration.toDays() > 0) return duration.toDays() + " días";
        if (duration.toHours() > 0) return duration.toHours() + " horas";
        if (duration.toMinutes() > 0) return duration.toMinutes() + " minutos";
        return "Hace un momento";
    }

    public String calculateDuration(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) {
            return "--";
        }

        long seconds = Duration.between(start, end).getSeconds();
        return String.format("%d min %d seg", seconds / 60, seconds % 60);
    }

    // ==================== Formatting Methods ====================

    public List<DailyProgressDTO> generateWeeklyProgress(List<Reproduction> completed) {
        List<DailyProgressDTO> progress = new ArrayList<>();
        LocalDate today = LocalDate.now();
        Locale spanishLocale = Locale.of("es", "ES");

        for (int i = 6; i >= 0; i--) {
            LocalDate date = today.minusDays(i);
            String dayName = date.getDayOfWeek().getDisplayName(TextStyle.SHORT, spanishLocale);
            dayName = dayName.substring(0, 1).toUpperCase() + dayName.substring(1);

            long count = completed.stream()
                    .filter(r -> r.getEndedAt() != null && r.getEndedAt().toLocalDate().equals(date))
                    .count();

            progress.add(new DailyProgressDTO(dayName, (int) count));
        }
        return progress;
    }

    public List<CategoryStatDTO> getCategoriesPlayed(List<Reproduction> completed) {
        if (completed.isEmpty()) return List.of();

        Map<String, Long> categoryCounts = completed.stream()
                .filter(r -> r.getRoutineSegment() != null && r.getRoutineSegment().getSequence() != null && r.getRoutineSegment().getSequence().getCategory() != null)
                .collect(Collectors.groupingBy(
                        r -> r.getRoutineSegment().getSequence().getCategory().getName(),
                        Collectors.counting()
                ));

        long total = categoryCounts.values().stream().mapToLong(Long::longValue).sum();

        return categoryCounts.entrySet().stream()
                .map(entry -> new CategoryStatDTO(
                        entry.getKey(),
                        (int) Math.round((double) entry.getValue() / total * 100)
                ))
                .sorted((c1, c2) -> Integer.compare(c2.porcentaje(), c1.porcentaje()))
                .toList();
    }

    public RecentActivityDTO mapToRecentActivityDTO(Reproduction r) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm", Locale.of("es", "ES"));

        String duracion = "N/A";
        if (r.getEndedAt() != null && r.getStartedAt() != null) {
            long minutes = Duration.between(r.getStartedAt(), r.getEndedAt()).toMinutes();
            duracion = minutes + " min";
        }

        String sequenceTitle = "Secuencia eliminada";
        if (r.getRoutineSegment() != null && r.getRoutineSegment().getSequence() != null && r.getRoutineSegment().getSequence().getTitle() != null && !r.getRoutineSegment().getSequence().getTitle().isBlank()) {
            sequenceTitle = r.getRoutineSegment().getSequence().getTitle();
        }

        return new RecentActivityDTO(
                r.getId(),
                sequenceTitle,
                r.getStartedAt() != null ? r.getStartedAt().format(formatter) : "",
                duracion,
                r.getStatus() == Status.COMPLETED
        );
    }

    public AssignedSequenceProgressDTO mapToAgendaDTO(RoutineSegment segment, LocalDateTime now, Set<Long> completedSegmentIdsToday) {
        boolean isCompleted = completedSegmentIdsToday.contains(segment.getId());

        String estado = isCompleted ? "COMPLETADA" :
                (segment.getEndTime() != null && now.toLocalTime().isAfter(segment.getEndTime()) ? "CADUCADA" : "PENDIENTE");

        String categoryName = segment.getSequence() != null && segment.getSequence().getCategory() != null
                ? segment.getSequence().getCategory().getName()
                : "Sin categoría";

        String franjaHoraria = segment.getStartTime() != null
                ? segment.getStartTime() + (segment.getEndTime() != null ? " - " + segment.getEndTime() : "")
                : (segment.getEndTime() != null ? "- " + segment.getEndTime() : "Sin horario");

        String sequenceTitle = "Secuencia eliminada";
        if (segment.getSequence() != null && segment.getSequence().getTitle() != null && !segment.getSequence().getTitle().isBlank()) {
            sequenceTitle = segment.getSequence().getTitle();
        }

        return new AssignedSequenceProgressDTO(
                segment.getId(),
                sequenceTitle,
                categoryName,
                franjaHoraria,
                estado
        );
    }

    public ButtonClicksDTO extractButtonClicks(Map<String, Integer> clicksMap) {
        if (clicksMap == null) {
            return new ButtonClicksDTO(0, 0, 0, 0);
        }

        return new ButtonClicksDTO(
                clicksMap.getOrDefault("previous", 0),
                clicksMap.getOrDefault("next", 0),
                clicksMap.getOrDefault("complete", 0),
                clicksMap.getOrDefault("exit", 0)
        );
    }

    public List<StepTrackingDTO> extractStepTracking(Reproduction rep) {
        if (rep == null || rep.getRoutineSegment() == null || rep.getRoutineSegment().getSequence() == null || rep.getRoutineSegment().getSequence().getSteps() == null) {
            return List.of();
        }

        Map<Integer, Integer> stepReproductions = rep.getStepReproductions() != null ? rep.getStepReproductions() : Map.of();
        Map<Integer, Long> reproductionTime = rep.getReproductionTime() != null ? rep.getReproductionTime() : Map.of();

        AtomicInteger indexCounter = new AtomicInteger(0);

        return rep.getRoutineSegment().getSequence().getSteps().stream()
                .map(step -> {
                    int stepIndex = indexCounter.getAndIncrement();

                    Integer views = stepReproductions.getOrDefault(stepIndex, 0);
                    Long timeMs = reproductionTime.getOrDefault(stepIndex, 0L);

                    String formattedTime = (timeMs / 1000) + " seg";

                    return new StepTrackingDTO(
                            stepIndex + 1,
                            step.getName(),
                            views,
                            formattedTime
                    );
                }).toList();
    }

    // ==================== Stats Calculation Methods ====================

    public StudentStatsDTO calculateStudentStats(Long studentId) {
        Student student = studentModelService.getById(studentId);
        List<Reproduction> allReproductions = reproductionModelService.findByStudentId(studentId);

        List<Reproduction> completedReproductions = allReproductions.stream()
                .filter(r -> r.getStatus() == Status.COMPLETED && r.getEndedAt() != null)
                .toList();

        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        DaysOfTheWeek todayEnum = getTodayEnum();
        Set<Long> completedSegmentIdsToday = new HashSet<>(reproductionModelService.findCompletedRoutineSegmentIdsForToday(studentId, today));

        int completadasHoy = (int) completedReproductions.stream()
                .filter(r -> r.getEndedAt().toLocalDate().equals(today))
                .count();

        List<RoutineSegment> segmentosHoy = student.getRoutines().stream()
                .filter(r -> r.getDaysOfTheWeek().contains(todayEnum))
                .flatMap(r -> r.getSequences().stream())
                .toList();

        int pendientesHoy = (int) segmentosHoy.stream()
                .filter(segment -> !completedSegmentIdsToday.contains(segment.getId()))
                .filter(segment -> segment.getEndTime() == null || !now.isAfter(segment.getEndTime()))
                .count();

        int totalAsignadasHoy = segmentosHoy.size();
        int completadasAsignadasHoy = (int) segmentosHoy.stream()
                .filter(segment -> completedSegmentIdsToday.contains(segment.getId()))
                .count();

        int tasaExito = totalAsignadasHoy == 0 ? 0 :
                (int) Math.round((double) completadasAsignadasHoy / totalAsignadasHoy * 100);

        Reproduction last = reproductionModelService.findFirstByStudentIdOrderByStartedAtDesc(studentId);
        List<Date> activityDates = reproductionModelService.findDistinctReproductionDatesByStudentId(studentId);

        Double avgTime = completedReproductions.stream()
                .mapToLong(r -> Duration.between(r.getStartedAt(), r.getEndedAt()).toMinutes())
                .average().orElse(0.0);

        return new StudentStatsDTO(
                completadasHoy,
                pendientesHoy,
                tasaExito,
                Math.round(avgTime * 10.0) / 10.0,
                calculateStreak(activityDates),
                calculateElapsedTime(last != null ? last.getStartedAt() : null)
        );
    }

    public List<DailyProgressDTO> getWeeklyProgress(Long studentId) {
        List<Reproduction> completed = reproductionModelService.findByStudentIdAndStartedAtAfter(
                        studentId, LocalDateTime.now().minusDays(7))
                .stream().filter(r -> r.getStatus() == Status.COMPLETED).toList();
        return generateWeeklyProgress(completed);
    }

    public List<CategoryStatDTO> getCategoryStats(Long studentId) {
        List<Reproduction> completed = reproductionModelService.findByStudentId(studentId).stream()
                .filter(r -> r.getStatus() == Status.COMPLETED).toList();
        return getCategoriesPlayed(completed);
    }

    public Page<AssignedSequenceProgressDTO> getStudentAgenda(Long studentId, Pageable pageable) {
        Student student = studentModelService.getById(studentId);
        LocalDate today = LocalDate.now();
        LocalDateTime now = LocalDateTime.now();
        DaysOfTheWeek todayEnum = getTodayEnum();
        Set<Long> completedSegmentIdsToday = new HashSet<>(reproductionModelService.findCompletedRoutineSegmentIdsForToday(studentId, today));

        List<AssignedSequenceProgressDTO> fullAgenda = student.getRoutines().stream()
                .filter(r -> r.getDaysOfTheWeek().contains(todayEnum))
                .flatMap(r -> r.getSequences().stream())
                .map(segment -> mapToAgendaDTO(segment, now, completedSegmentIdsToday))
                .sorted(Comparator.comparing(AssignedSequenceProgressDTO::franjaHoraria, Comparator.nullsLast(String::compareTo)))
                .toList();

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), fullAgenda.size());

        if (start > fullAgenda.size()) return new PageImpl<>(List.of(), pageable, fullAgenda.size());

        return new PageImpl<>(fullAgenda.subList(start, end), pageable, fullAgenda.size());
    }

    public Page<RecentActivityDTO> getStudentActivity(Long studentId, Pageable pageable) {
        return reproductionModelService.findByStudentIdOrderByStartedAtDesc(studentId, pageable)
                .map(this::mapToRecentActivityDTO);
    }

    public ReproductionDetailDTO getReproductionDetail(Long reproductionId) {
        Reproduction rep = reproductionModelService.findById(reproductionId)
                .orElseThrow(() -> new NoSuchElementException("No se encontró la reproducción con id: " + reproductionId));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        String sequenceTitle = "Secuencia eliminada";
        String categoryName = "Sin categoría";
        String studentFullName = "--";

        if (rep.getRoutineSegment() != null && rep.getRoutineSegment().getSequence() != null) {
            var seq = rep.getRoutineSegment().getSequence();
            if (seq.getTitle() != null && !seq.getTitle().isBlank()) {
                sequenceTitle = seq.getTitle();
            }
            if (seq.getCategory() != null && seq.getCategory().getName() != null && !seq.getCategory().getName().isBlank()) {
                categoryName = seq.getCategory().getName();
            }
        }

        if (rep.getStudent() != null && rep.getStudent().getUser() != null) {
            var u = rep.getStudent().getUser();
            String n = u.getName() != null ? u.getName() : "";
            String ln = u.getLastName() != null ? u.getLastName() : "";
            String combined = (n + " " + ln).trim();
            if (!combined.isBlank()) studentFullName = combined;
        }

        ButtonClicksDTO buttons = extractButtonClicks(rep.getButtonsClicks());
        List<StepTrackingDTO> steps = extractStepTracking(rep);

        return new ReproductionDetailDTO(
                rep.getId(),
                sequenceTitle,
                studentFullName,
                categoryName,
                rep.getStatus() != null ? rep.getStatus().name() : "--",
                rep.getStartedAt() != null ? rep.getStartedAt().format(dtf) : "--",
                rep.getEndedAt() != null ? rep.getEndedAt().format(dtf) : "En curso",
                calculateDuration(rep.getStartedAt(), rep.getEndedAt()),
                buttons,
                steps
        );
    }

    // ==================== Punctuation Calculation (Perfil) ====================

    public int calculateCompletedReproductions(Long studentId) {
        List<Reproduction> allReproductions = reproductionModelService.findByStudentId(studentId);
        return (int) allReproductions.stream()
                .filter(r -> r.getStatus() == Status.COMPLETED && r.getEndedAt() != null)
                .count();
    }

    public int calculatePendingToday(Long studentId) {
        Student student = studentModelService.getById(studentId);
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        DaysOfTheWeek todayEnum = getTodayEnum();

        Set<Long> completedSegmentIdsToday = new HashSet<>(reproductionModelService.findCompletedRoutineSegmentIdsForToday(studentId, today));

        List<RoutineSegment> segmentosHoy = student.getRoutines().stream()
                .filter(r -> r.getDaysOfTheWeek().contains(todayEnum))
                .flatMap(r -> r.getSequences().stream())
                .toList();

        return (int) segmentosHoy.stream()
                .filter(segment -> !completedSegmentIdsToday.contains(segment.getId()))
                .filter(segment -> segment.getEndTime() == null || !now.isAfter(segment.getEndTime()))
                .count();
    }

    public Integer calculateStreakForStudent(Long studentId) {
        List<Date> activityDates = reproductionModelService.findDistinctReproductionDatesByStudentId(studentId);
        return calculateStreak(activityDates);
    }

}
