package com.sacristan.api.OLDinterfaces.student.services.routine;

import com.sacristan.api.global.models.*;
import com.sacristan.api.global.models.user.User;
import com.sacristan.api.global.repositories.ReproductionRepository;
import com.sacristan.api.global.repositories.RoutineSequenceRepository;
import com.sacristan.api.interfaces.student.dtos.sequence.PrincipalSequenceDTO;
import com.sacristan.api.interfaces.student.services.user.StudentUserUtilsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentRoutineService {

    private final StudentUserUtilsService studentUserUtilsService;
    private final ReproductionRepository reproductionRepository;

    public Page<PrincipalSequenceDTO> getPendingSequences(Pageable pageable, User user) {
        List<Routine> routines = studentUserUtilsService.getRoutinesByUserId(user);

        List<RoutineSequence> pendingSequences = routines.stream()
                .filter(r -> r.getDaysOfTheWeek().contains(DaysOfTheWeek.valueOf(LocalDate.now().getDayOfWeek().name())))
                .map(Routine::getSequences)
                .flatMap(List::stream)
                .filter(rs -> {
                    List<Reproduction> reproductions = reproductionRepository.findCompletedRoutineSequenceForToday(
                            user.getId(),
                            rs.getId(),
                            LocalDate.now().atStartOfDay(),
                            LocalDate.now().atStartOfDay().plusDays(1),
                            Status.COMPLETED
                    );
                    return !reproductions
                            .stream()
                            .map(Reproduction::getRoutineSequence)
                            .map(RoutineSequence::getId)
                            .toList()
                            .contains(rs.getId());
                })
                .filter(rs ->
                        rs.getStartTime().isBefore(LocalTime.now()) && rs.getEndTime().isAfter(LocalTime.now())
                ).toList();

        List<PrincipalSequenceDTO> pendingSequenceDTOs = new ArrayList<>();

        for (RoutineSequence routineSequence : pendingSequences) {
            Sequence s = routineSequence.getSequence();
            pendingSequenceDTOs.add(PrincipalSequenceDTO.from(s, routineSequence.getStartTime(), routineSequence.getId()));
        }

        return new PageImpl<>(pendingSequenceDTOs, pageable, pendingSequenceDTOs.size());
    }
}
