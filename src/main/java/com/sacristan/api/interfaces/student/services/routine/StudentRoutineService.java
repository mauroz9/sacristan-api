package com.sacristan.api.interfaces.student.services.routine;

import com.sacristan.api.global.models.DaysOfTheWeek;
import com.sacristan.api.global.models.Routine;
import com.sacristan.api.global.models.RoutineSequence;
import com.sacristan.api.global.models.Sequence;
import com.sacristan.api.global.models.user.User;
import com.sacristan.api.interfaces.student.dtos.sequence.PrincipalSequenceDTO;
import com.sacristan.api.interfaces.student.services.user.StudentUserUtilsService;
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
public class StudentRoutineService {

    private final StudentUserUtilsService studentUserUtilsService;

    public Page<PrincipalSequenceDTO> getPendingSequences(Pageable pageable, User user) {
        List<Routine> routines = studentUserUtilsService.getRoutinesByUserId(user);

        List<RoutineSequence> pendingSequences = routines.stream()
                .filter(r -> r.getDaysOfTheWeek().contains(DaysOfTheWeek.valueOf(LocalDate.now().getDayOfWeek().name())))
                .map(Routine::getSequences)
                .flatMap(List::stream)
                .filter(rs ->
                        rs.getStartTime().isAfter(LocalTime.now().minusMinutes(15))
                    && rs.getStartTime().isBefore(LocalTime.now().plusMinutes(30))
                ).toList();

        List<PrincipalSequenceDTO> pendingSequenceDTOs = new ArrayList<>();

        for (RoutineSequence routineSequence : pendingSequences) {
            Sequence s = routineSequence.getSequence();
            pendingSequenceDTOs.add(PrincipalSequenceDTO.from(s, routineSequence.getStartTime()));
        }

        return new PageImpl<>(pendingSequenceDTOs, pageable, pendingSequenceDTOs.size());
    }
}
