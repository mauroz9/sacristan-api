package com.sacristan.api.interfaces.student.reproduccion;

import com.sacristan.api.global.entities.assignments.routineSegment.RoutineSegment;
import com.sacristan.api.global.entities.assignments.routineSegment.RoutineSegmentModelService;
import com.sacristan.api.global.entities.content.rotuine.Routine;
import com.sacristan.api.global.entities.content.sequence.Sequence;
import com.sacristan.api.global.entities.content.sequence.SequenceModelService;
import com.sacristan.api.global.entities.tracking.reproduction.Reproduction;
import com.sacristan.api.global.entities.tracking.reproduction.ReproductionModelService;
import com.sacristan.api.global.entities.tracking.status.Status;
import com.sacristan.api.global.entities.users.student.StudentModelService;
import com.sacristan.api.global.entities.users.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ReproduccionService {

    private final SequenceModelService  sequenceModelService;
    private final RoutineSegmentModelService routineSegmentModelService;
    private final StudentModelService  studentModelService;
    private final ReproductionModelService reproductionModelService;

    public Sequence getSequenceDetails(Long id) {
        return sequenceModelService.getById(id);
    }

    public Long startReproduction(Long id, User user) {
        RoutineSegment routineSequence = routineSegmentModelService.getById(id);

        Reproduction reproduction = Reproduction.builder()
                .routineSegment(routineSequence)
                .startedAt(LocalDateTime.now())
                .endedAt(null)
                .status(Status.IN_PROGRESS)
                .student(studentModelService.getById(user.getId()))
                .build();
        return reproductionModelService.save(reproduction).getId();
    }

    public void endReproduction(Long id, User user) {
        Reproduction reproduction = reproductionModelService.getById(id);

        if (!reproduction.getStudent().getId().equals(user.getId())) {
            throw new IllegalArgumentException("You can only finish your own reproductions");
        }

        reproduction.setEndedAt(LocalDateTime.now());
        reproduction.setStatus(Status.COMPLETED);
        reproductionModelService.save(reproduction);
    }
}
