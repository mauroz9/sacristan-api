package com.sacristan.api.OLDinterfaces.student.services.sequence;

import com.sacristan.api.global.models.Sequence;
import com.sacristan.api.global.models.user.User;
import com.sacristan.api.global.repositories.SequenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StudentSequenceUtilsService {

    private final SequenceRepository sequenceRepository;

    public Sequence getSequenceById(Long id) {
        return sequenceRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Secuencia no encontrada o no pertenece al usuario"));
    }

}
