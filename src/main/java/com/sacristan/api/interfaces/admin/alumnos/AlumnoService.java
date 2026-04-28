package com.sacristan.api.interfaces.admin.alumnos;

import com.sacristan.api.global.dtos.SortParamDTO;
import com.sacristan.api.global.entities.content.rotuine.Routine;
import com.sacristan.api.global.entities.content.rotuine.RoutineModelService;
import com.sacristan.api.global.entities.content.sequence.Sequence;
import com.sacristan.api.global.entities.content.sequence.SequenceModelService;
import com.sacristan.api.global.entities.content.weekDays.DaysOfTheWeek;
import com.sacristan.api.global.entities.tracking.reproduction.Reproduction;
import com.sacristan.api.global.entities.tracking.reproduction.ReproductionModelService;
import com.sacristan.api.global.entities.tracking.status.Status;
import com.sacristan.api.global.entities.users.role.Role;
import com.sacristan.api.global.entities.users.student.Student;
import com.sacristan.api.global.entities.users.student.StudentModelService;
import com.sacristan.api.global.entities.users.student.StudentSpecification;
import com.sacristan.api.global.entities.users.user.User;
import com.sacristan.api.global.entities.users.user.UserModelService;
import com.sacristan.api.interfaces.admin.alumnos.dtos.response.dashboard.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlumnoService {

    private final StudentModelService studentModelService;
    private final UserModelService userModelService;
    private final SequenceModelService sequenceModelService;
    private final RoutineModelService routineModelService;
    private final ReproductionModelService reproductionModelService;

    public void create(Student student) {
        User createdUser = userModelService.create(student.getUser(), Role.STUDENT);
        student.setUser(createdUser);
        studentModelService.save(student);
    }

    public void update(Long id, User user) {
        userModelService.update(id, user);
    }

    public void delete(Long id) {
        reproductionModelService.deleteByStudentId(id);
        studentModelService.deleteById(id);
        userModelService.deleteById(id);
    }

    public Student getById(Long id) {
        return studentModelService.getById(id);
    }

    public Page<Student> list(Pageable pageable, String q) {
        return studentModelService.findByTerm(pageable, StudentSpecification.searchByTerm(q));
    }

    public List<SortParamDTO> getSortParams() {
        return StudentModelService.getSortParams();
    }

    public Student assignSequenceToStudent(Long studentId, Long sequenceId) {
        Student student = studentModelService.getById(studentId);
        Sequence sequence = sequenceModelService.getById(sequenceId);

        if (student.getSequences().contains(sequence)) {
            throw new IllegalArgumentException("Sequence with id: " + sequenceId + " is already assigned to student with id: " + studentId);
        }

        student.assignSequence(sequence);
        return studentModelService.save(student);
    }

    public Student unassignSequenceFromStudent(Long studentId, Long sequenceId) {
        Student student = studentModelService.getById(studentId);
        Sequence sequence = sequenceModelService.getById(sequenceId);

        if (!student.getSequences().contains(sequence)) {
            throw new IllegalArgumentException("Sequence with id: " + sequenceId + " is not assigned to student with id: " + studentId);
        }

        student.unassignSequence(sequence);
        return studentModelService.save(student);
    }

    public Set<Sequence> getSequencesOfStudent(Long studentId) {
        Student student = studentModelService.getById(studentId);
        return student.getSequences();
    }

    public List<Sequence> getUnassignedSequencesOfStudent(Long studentId) {
        Student student = studentModelService.getById(studentId);
        try {
            List<Sequence> allSequences = sequenceModelService.listAll();
            allSequences.removeAll(student.getSequences());
            return allSequences;
        } catch (Exception e) {
            return List.of();
        }
    }

    public Integer countAssignedSequencesOfStudent(Long studentId) {
        Student student = studentModelService.getById(studentId);
        return student.getSequences().size();
    }

    public Student assignRoutineToStudent(Long studentId, Long routineId) {
        Student student = studentModelService.getById(studentId);
        Routine routine = routineModelService.getById(routineId);

        if (student.getRoutines().contains(routine)) {
            throw new IllegalArgumentException("Routine with id: " + routineId + " is already assigned to student with id: " + studentId);
        }

        student.getRoutines().add(routine);
        return studentModelService.save(student);
    }

    public Student unassignRoutineFromStudent(Long studentId, Long routineId) {
        Student student = studentModelService.getById(studentId);
        Routine routine = routineModelService.getById(routineId);

        if (!student.getRoutines().contains(routine)) {
            throw new IllegalArgumentException("Routine with id: " + routineId + " is not assigned to student with id: " + studentId);
        }

        student.getRoutines().remove(routine);
        return studentModelService.save(student);
    }

    public Set<Routine> getRoutinesOfStudent(Long studentId) {
        Student student = studentModelService.getById(studentId);
        return student.getRoutines();
    }

    public Set<Routine> getUnassignedRoutinesOfStudent(Long studentId) {
        Student student = studentModelService.getById(studentId);
        try {
            return routineModelService.listAll()
                    .stream()
                    .filter(r -> !student.getRoutines().contains(r))
                    .collect(Collectors.toSet());
        } catch (Exception e) {
            return Set.of();
        }
    }

    public Integer countAssignedRoutinesOfStudent(Long studentId) {
        Student student = studentModelService.getById(studentId);
        return student.getRoutines().size();
    }

    private Integer calculateStreak(List<Date> dates) {
        if (dates == null || dates.isEmpty()) return 0;
        int racha = 1;

        LocalDate today = LocalDate.now();
        LocalDate currentCheck = dates.get(0).toLocalDate();

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

    private String calculateElapsedTime(LocalDateTime time) {
        if (time == null) return "Sin actividad";
        Duration duration = Duration.between(time, LocalDateTime.now());
        if (duration.toDays() > 0) return duration.toDays() + " días";
        if (duration.toHours() > 0) return duration.toHours() + " horas";
        if (duration.toMinutes() > 0) return duration.toMinutes() + " minutos";
        return "Hace un momento";
    }

    private List<DailyProgressDTO> generateWeeklyProgress(List<Reproduction> completed) {
        List<DailyProgressDTO> progress = new ArrayList<>();
        LocalDate today = LocalDate.now();

        for (int i = 6; i >= 0; i--) {
            LocalDate date = today.minusDays(i);
            String dayName = date.getDayOfWeek().getDisplayName(TextStyle.SHORT, new Locale("es", "ES"));
            dayName = dayName.substring(0, 1).toUpperCase() + dayName.substring(1); // Capitalizar

            long count = completed.stream()
                    .filter(r -> r.getEndedAt() != null && r.getEndedAt().toLocalDate().equals(date))
                    .count();

            progress.add(new DailyProgressDTO(dayName, (int) count));
        }
        return progress;
    }

    private List<CategoryStatDTO> getCategoriesPlayed(List<Reproduction> completed) {
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

    private RecentActivityDTO mapToRecentActivityDTO(Reproduction r) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm", new Locale("es", "ES"));

        String duracion = "N/A";
        if (r.getEndedAt() != null && r.getStartedAt() != null) {
            long minutes = Duration.between(r.getStartedAt(), r.getEndedAt()).toMinutes();
            duracion = minutes + " min";
        }

        String sequenceTitle = r.getRoutineSegment() != null && r.getRoutineSegment().getSequence() != null
                ? r.getRoutineSegment().getSequence().getTitle()
                : "Secuencia eliminada";

        return new RecentActivityDTO(
                r.getId(),
                sequenceTitle,
                r.getStartedAt() != null ? r.getStartedAt().format(formatter) : "",
                duracion,
                r.getStatus() == Status.COMPLETED
        );
    }

    private DaysOfTheWeek getTodayEnum() {
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

    public StudentDashboardResponse getStudentDashboardData(Long studentId) {
        Student student = getById(studentId); // Lanza excepción si no existe

        Integer completadas = reproductionModelService.countByStudentIdAndStatus(studentId, Status.COMPLETED);
        Integer enProgreso = reproductionModelService.countByStudentIdAndStatus(studentId, Status.IN_PROGRESS);
        int totalIntentos = completadas + enProgreso;
        Integer tasaExito = totalIntentos == 0 ? 0 : (int) Math.round((double) completadas / totalIntentos * 100);

        Reproduction lastReproduction = reproductionModelService.findFirstByStudentIdOrderByStartedAtDesc(studentId);
        String ultimaActividadStr = calculateElapsedTime(lastReproduction != null ? lastReproduction.getStartedAt() : null);

        List<java.sql.Date> activityDates = reproductionModelService.findDistinctReproductionDatesByStudentId(studentId);
        Integer racha = calculateStreak(activityDates);

        List<Reproduction> allReproductions = reproductionModelService.findByStudentId(studentId);
        List<Reproduction> completedReproductions = allReproductions.stream()
                .filter(r -> r.getStatus() == Status.COMPLETED && r.getEndedAt() != null)
                .toList();

        Double tiempoPromedio = completedReproductions.stream()
                .mapToLong(r -> Duration.between(r.getStartedAt(), r.getEndedAt()).toMinutes())
                .average()
                .orElse(0.0);
        tiempoPromedio = Math.round(tiempoPromedio * 10.0) / 10.0; // Redondear a 1 decimal

        StudentStatsDTO statsDTO = new StudentStatsDTO(
                completadas, enProgreso, tasaExito, tiempoPromedio, racha, ultimaActividadStr
        );

        List<DailyProgressDTO> weeklyProgress = generateWeeklyProgress(completedReproductions);

        List<CategoryStatDTO> topCategories = getCategoriesPlayed(completedReproductions);

        List<RecentActivityDTO> recentActivity = reproductionModelService.findTop10ByStudentIdOrderByStartedAtDesc(studentId)
                .stream()
                .map(this::mapToRecentActivityDTO)
                .toList();

        LocalDate todayDate = LocalDate.now();
        LocalTime nowTime = LocalTime.now();
        DaysOfTheWeek todayEnum = getTodayEnum();

        List<AssignedSequenceProgressDTO> assignedProgress = student.getRoutines().stream()
                .filter(routine -> routine.getDaysOfTheWeek().contains(todayEnum))
                .flatMap(routine -> routine.getSequences().stream())
                .map(segment -> {
                    boolean isCompleted = completedReproductions.stream()
                            .anyMatch(r -> r.getRoutineSegment() != null
                                    && r.getRoutineSegment().getId().equals(segment.getId())
                                    && r.getEndedAt() != null
                                    && r.getEndedAt().toLocalDate().equals(todayDate));

                    String estado;
                    if (isCompleted) {
                        estado = "COMPLETADA";
                    } else if (segment.getEndTime() != null && nowTime.isAfter(segment.getEndTime())) {
                        estado = "CADUCADA";
                    } else {
                        estado = "PENDIENTE";
                    }

                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                    String start = segment.getStartTime() != null ? segment.getStartTime().format(timeFormatter) : "--:--";
                    String end = segment.getEndTime() != null ? segment.getEndTime().format(timeFormatter) : "--:--";
                    String franja = start + " - " + end;

                    return new AssignedSequenceProgressDTO(
                            segment.getId(),
                            segment.getSequence().getTitle(),
                            segment.getSequence().getCategory() != null ? segment.getSequence().getCategory().getName() : "Sin categoría",
                            franja,
                            estado
                    );
                })
                .sorted(Comparator.comparing(AssignedSequenceProgressDTO::franjaHoraria))
                .toList();

        return new StudentDashboardResponse(statsDTO, weeklyProgress, topCategories, assignedProgress, recentActivity);
    }
}

