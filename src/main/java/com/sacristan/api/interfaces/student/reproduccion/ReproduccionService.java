package com.sacristan.api.interfaces.student.reproduccion;

import com.sacristan.api.global.entities.content.sequence.Sequence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReproduccionService {

    // * SEQUENCES
    public Sequence getSequenceDetails(Long id) {
        // TODO: Implement get sequence details logic
        return null;
    }

    // * REPRODUCTIONS
    public Object startReproduction(Long id) {
        // TODO: Implement start reproduction logic
        return null;
    }

    public void endReproduction(Long id) {
        // TODO: Implement end reproduction logic
    }
}
