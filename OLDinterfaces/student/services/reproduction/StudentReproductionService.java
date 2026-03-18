package com.sacristan.api.OLDinterfaces.student.services.reproduction;

import com.sacristan.api.global.models.Reproduction;
import com.sacristan.api.global.models.RoutineSequence;
import com.sacristan.api.global.models.Status;
import com.sacristan.api.global.models.user.User;
import com.sacristan.api.global.repositories.ReproductionRepository;
import com.sacristan.api.global.repositories.RoutineSequenceRepository;
import com.sacristan.api.global.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StudentReproductionService {

    private final ReproductionRepository reproductionRepository;
    private final RoutineSequenceRepository routineSequenceRepository;
    private final StudentRepository studentRepository;

    public Long reproduce(Long routineSequenceId, User user) {

        System.out.println(routineSequenceId);

        RoutineSequence routineSequence = routineSequenceRepository.findById(routineSequenceId)
                .orElseThrow(() -> new NoSuchElementException("Routine sequence not found"));

        Reproduction reproduction = Reproduction.builder()
                .routineSequence(routineSequence)
                .startedAt(LocalDateTime.now())
                .endedAt(null)
                .status(Status.IN_PROGRESS)
                .student(studentRepository.findById(user.getId()).orElseThrow(() -> new NoSuchElementException("Student not found")))
                .build();
        return reproductionRepository.save(reproduction).getId();
    }

    public void finish(Long id, User user) {
        Reproduction reproduction = reproductionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Reproduction not found"));

        if (!reproduction.getStudent().getId().equals(user.getId())) {
            throw new IllegalArgumentException("You can only finish your own reproductions");
        }

        reproduction.setEndedAt(LocalDateTime.now());
        reproduction.setStatus(Status.COMPLETED);
        reproductionRepository.save(reproduction);
    }

}
