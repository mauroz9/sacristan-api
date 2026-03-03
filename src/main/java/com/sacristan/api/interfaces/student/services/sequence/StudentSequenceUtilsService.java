package com.sacristan.api.interfaces.student.services.sequence;

import com.sacristan.api.global.models.Sequence;
import com.sacristan.api.global.models.user.User;
import com.sacristan.api.global.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.PredicateSpecification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class StudentSequenceUtilsService {

    private final StudentRepository studentRepository;

    public List<Sequence> getSequencesByUserId(User user) {
        return studentRepository.findAllSequencesByUserId(user.getId());
    }

    public Sequence getSequenceByIdAndUserId(Long id, User user) {
        return studentRepository.findSequenceByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new NoSuchElementException("Secuencia no encontrada o no pertenece al usuario"));
    }
}
