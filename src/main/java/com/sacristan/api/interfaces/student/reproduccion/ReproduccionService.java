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
import com.sacristan.api.interfaces.student.reproduccion.dtos.request.EndReproductionRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
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

    public void endReproduction(Long id, EndReproductionRequest request, User user) {
        System.out.println("FINALIZANDO.....");
        Reproduction reproduction = reproductionModelService.getById(id);

        if (!reproduction.getStudent().getId().equals(user.getId())) {
            throw new IllegalArgumentException("You can only finish your own reproductions");
        }

        Map<String, Integer> buttonClicks = new HashMap<>();

        buttonClicks.put("previous", request.getButtonClicks().getPrevious());
        buttonClicks.put("next", request.getButtonClicks().getNext());

        if (request.getButtonClicks().getComplete() == 0 || request.getButtonClicks().getComplete() == null) {
            reproduction.setStatus(Status.ABANDONED);
        } else {
            reproduction.setStatus(Status.COMPLETED);
        }

        buttonClicks.put("complete",  request.getButtonClicks().getComplete());
        buttonClicks.put("exit",  request.getButtonClicks().getExit());

        reproduction.setButtonsClicks(buttonClicks);

        Map<Integer, Integer> stepReproduction = new HashMap<>();
        Map<Integer, Long> stepTime = new HashMap<>();

        request.getStepStats().forEach(step -> {
            stepReproduction.put(step.getStepIndex(), step.getViewCount());
            stepTime.put(step.getStepIndex(), step.getTotalViewTimeMs());
        });

        reproduction.setStepReproductions(stepReproduction);
        reproduction.setReproductionTime(stepTime);


        reproduction.setEndedAt(LocalDateTime.now());
        reproductionModelService.save(reproduction);
    }

    private void logReproductionStats(EndReproductionRequest request) {
        System.out.println("Reproducción completada - ID: " + request.getReproductionId() +
                ", Total pasos: " + request.getTotalSteps() +
                ", Total clicks: " + request.getTotalClicks());

        if (request.getStepStats() != null && !request.getStepStats().isEmpty()) {
            System.out.println("Estadísticas por paso:");
            request.getStepStats().forEach(step ->
                    System.out.println("  Paso " + step.getStepIndex() + ": " +
                            step.getViewCount() + " visualizaciones, " +
                            step.getTotalViewTimeMs() + " ms de visualización total")
            );
        }

        if (request.getButtonClicks() != null) {
            System.out.println("Clicks de botones - Anterior: " +
                    request.getButtonClicks().getPrevious() +
                    ", Siguiente: " + request.getButtonClicks().getNext() +
                    ", Completar: " + request.getButtonClicks().getComplete() +
                    ", Salir: " + request.getButtonClicks().getExit());
        }
    }
}
