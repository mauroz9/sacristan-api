package com.sacristan.api.interfaces.user.services;

import com.sacristan.api.global.models.Sequence;
import com.sacristan.api.global.models.user.User;
import com.sacristan.api.global.repositories.SequenceRepository;
import com.sacristan.api.global.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserSequenceService {

    private final SequenceRepository repository;
    private final StudentRepository studentRepository;

    public @Nullable Page<Sequence> list(Pageable pageable, User user) {
        return studentRepository.findAllSequencesByUserId(pageable, user.getId());
    }
}
