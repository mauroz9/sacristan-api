package com.sacristan.api.interfaces.student.services.sequence;

import com.sacristan.api.global.models.Sequence;
import com.sacristan.api.global.models.user.User;
import com.sacristan.api.global.repositories.StudentRepository;
import com.sacristan.api.global.specifications.SequenceSpecification;
import com.sacristan.api.interfaces.student.services.user.StudentUserUtilsService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.PredicateSpecification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class StudentSequenceService {

    private final StudentUserUtilsService studentUserUtilsService;
    private final StudentSequenceUtilsService studentSequenceUtilsService;

    public Page<Sequence> list(Pageable pageable, User user, Long categoryId, String searchQuery) {
        return  studentUserUtilsService.list(pageable, user, categoryId, searchQuery);
    }

    public Sequence getById(Long id) {
        return studentSequenceUtilsService.getSequenceById(id);
    }




}
