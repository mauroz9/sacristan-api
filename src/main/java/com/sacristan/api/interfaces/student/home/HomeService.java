package com.sacristan.api.interfaces.student.home;

import com.sacristan.api.global.entities.assignments.routineSegment.RoutineSegment;
import com.sacristan.api.global.entities.content.rotuine.Routine;
import com.sacristan.api.global.entities.content.rotuine.RoutineModelService;
import com.sacristan.api.global.entities.content.sequence.Sequence;
import com.sacristan.api.global.entities.content.weekDays.DaysOfTheWeek;
import com.sacristan.api.global.entities.tracking.reproduction.Reproduction;
import com.sacristan.api.global.entities.tracking.reproduction.ReproductionModelService;
import com.sacristan.api.global.entities.tracking.status.Status;
import com.sacristan.api.global.entities.users.student.StudentModelService;
import com.sacristan.api.global.entities.users.user.User;
import com.sacristan.api.interfaces.student.home.dtos.response.PendingSequenceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final StudentModelService studentModelService;
    private final ReproductionModelService reproductionModelService;

    public Page<PendingSequenceResponse> getPendingSequences(Pageable pageable, User user) {
        List<Routine> routines = studentModelService.getRoutinesByUserId(user);

        System.out.println("Routines for user " + user.getEmail() + ": " + routines);

        List<RoutineSegment> pendingSequences = routines.stream()
                .filter(r -> r.getDaysOfTheWeek().contains(DaysOfTheWeek.valueOf(LocalDate.now().getDayOfWeek().name())))
                .map(Routine::getSequences)
                .flatMap(List::stream)
                .filter(rs -> {
                    List<Reproduction> reproductions = reproductionModelService.findCompletedRoutineSequenceForToday(
                            user.getId(),
                            rs.getId(),
                            LocalDate.now().atStartOfDay(),
                            LocalDate.now().atStartOfDay().plusDays(1),
                            Status.COMPLETED
                    );
                    System.out.println("Falla el filtro...");
                    return !reproductions
                            .stream()
                            .map(Reproduction::getRoutineSegment)
                            .map(RoutineSegment::getId)
                            .toList()
                            .contains(rs.getId());
                })
                .filter(rs ->
                        rs.getStartTime().isBefore(LocalTime.now()) && rs.getEndTime().isAfter(LocalTime.now())
                ).toList();

        List<PendingSequenceResponse> pendingSequenceDTOs = new ArrayList<>();

        System.out.println("Pending sequences for user " + user.getEmail() + ": " + pendingSequences);

        for (RoutineSegment routineSequence : pendingSequences) {
            Sequence s = routineSequence.getSequence();
            pendingSequenceDTOs.add(PendingSequenceResponse.from(s, routineSequence.getStartTime(), routineSequence.getId()));
        }

        System.out.println("TODO OK....");

        return new PageImpl<>(pendingSequenceDTOs, pageable, pendingSequenceDTOs.size());
    }
}

