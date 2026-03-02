package com.sacristan.api.interfaces.student.services.sequence;

import com.sacristan.api.global.models.Sequence;
import com.sacristan.api.global.models.user.User;
import com.sacristan.api.global.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class StudentSequenceService {

    private final StudentSequenceUtilsService studentSequenceUtilsService;

    public Page<Sequence> list(Pageable pageable, User user, Long categoryId) {
        List<Sequence> sequences = studentSequenceUtilsService.getSequencesByUserId(user);

        if (categoryId != null) {
            sequences = sequences.stream()
                    .filter(s -> s.getCategory() != null && s.getCategory().getId().equals(categoryId))
                    .toList();
        }

        return new PageImpl<>(
                sequences,
                pageable,
                sequences.size()
        );
    }
}
