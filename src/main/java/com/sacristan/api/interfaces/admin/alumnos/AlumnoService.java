package com.sacristan.api.interfaces.admin.alumnos;

import com.sacristan.api.global.dtos.SortParamDTO;
import com.sacristan.api.global.entities.assignments.routineSegment.RoutineSegment;
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

    public StudentStatsDTO getStudentStats(Long studentId) {
        Student student = getById(studentId);
        List<Reproduction> allReproductions = reproductionModelService.findByStudentId(studentId);

        List<Reproduction> completedReproductions = allReproductions.stream()
                .filter(r -> r.getStatus() == Status.COMPLETED && r.getEndedAt() != null)
                .toList();

        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        DaysOfTheWeek todayEnum = getTodayEnum();

        // A) Completadas Hoy (Globales, incluye tanto las mandadas como las que haga por libre)
        int completadasHoy = (int) completedReproductions.stream()
                .filter(r -> r.getEndedAt().toLocalDate().equals(today))
                .count();

        // --- LÓGICA DE LA AGENDA DIARIA ---
        // Extraemos la lista de todas las tareas (segmentos) que le tocan hacer HOY
        List<RoutineSegment> segmentosHoy = student.getRoutines().stream()
                .filter(r -> r.getDaysOfTheWeek().contains(todayEnum))
                .flatMap(r -> r.getSequences().stream())
                .toList();

        // B) Pendientes Hoy (Las que le tocan hoy, que NO ha hecho, y que no han caducado)
        int pendientesHoy = (int) segmentosHoy.stream()
                .filter(segment -> !reproductionModelService.existsCompletedToday(studentId, segment.getId(), today))
                .filter(segment -> segment.getEndTime() == null || !now.isAfter(segment.getEndTime()))
                .count();

        // C) Tasa de Éxito de Hoy (Completadas de la agenda vs Total de la agenda de hoy)
        int totalAsignadasHoy = segmentosHoy.size();
        int completadasAsignadasHoy = (int) segmentosHoy.stream()
                .filter(segment -> reproductionModelService.existsCompletedToday(studentId, segment.getId(), today))
                .count();

        int tasaExito = totalAsignadasHoy == 0 ? 0 :
                (int) Math.round((double) completadasAsignadasHoy / totalAsignadasHoy * 100);

        // D) Resto de Estadísticas (Racha, Media de tiempo, etc.)
        Reproduction last = reproductionModelService.findFirstByStudentIdOrderByStartedAtDesc(studentId);
        List<java.sql.Date> activityDates = reproductionModelService.findDistinctReproductionDatesByStudentId(studentId);

        Double avgTime = completedReproductions.stream()
                .mapToLong(r -> Duration.between(r.getStartedAt(), r.getEndedAt()).toMinutes())
                .average().orElse(0.0);

        return new StudentStatsDTO(
                completadasHoy,
                pendientesHoy,
                tasaExito, // Ahora es % del cumplimiento de la agenda diaria
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

    private AssignedSequenceProgressDTO mapToAgendaDTO(RoutineSegment segment, Long studentId, LocalDate today, LocalTime now) {
        boolean isCompleted = reproductionModelService.existsCompletedToday(studentId, segment.getId(), today);

        String estado = isCompleted ? "COMPLETADA" :
                (segment.getEndTime() != null && now.isAfter(segment.getEndTime()) ? "CADUCADA" : "PENDIENTE");

        return new AssignedSequenceProgressDTO(
                segment.getId(),
                segment.getSequence().getTitle(),
                segment.getSequence().getCategory().getName(),
                segment.getStartTime() + " - " + segment.getEndTime(),
                estado
        );
    }

    public Page<AssignedSequenceProgressDTO> getStudentAgenda(Long studentId, Pageable pageable) {
        Student student = getById(studentId);
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        DaysOfTheWeek todayEnum = getTodayEnum();

        List<AssignedSequenceProgressDTO> fullAgenda = student.getRoutines().stream()
                .filter(r -> r.getDaysOfTheWeek().contains(todayEnum))
                .flatMap(r -> r.getSequences().stream())
                .map(segment -> mapToAgendaDTO(segment, studentId, today, now))
                .sorted(Comparator.comparing(AssignedSequenceProgressDTO::franjaHoraria))
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
}

