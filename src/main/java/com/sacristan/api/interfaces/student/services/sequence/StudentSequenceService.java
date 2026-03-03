package com.sacristan.api.interfaces.student.services.sequence;

import com.sacristan.api.global.models.Sequence;
import com.sacristan.api.global.models.user.User;
import com.sacristan.api.global.repositories.StudentRepository;
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

    private final StudentSequenceUtilsService studentSequenceUtilsService;

    public Page<Sequence> list(Pageable pageable, User user, Long categoryId, String searchQuery) {
        List<Sequence> sequences = studentSequenceUtilsService.getSequencesByUserId(user);

        if (searchQuery != null && !searchQuery.isBlank()) {

            sequences = sequences.stream()
                .filter(s ->
                        (s.getTitle() != null && s.getTitle().toLowerCase().contains(searchQuery.toLowerCase())) ||
                        (s.getDescription() != null && s.getDescription().toLowerCase().contains(searchQuery.toLowerCase()))
                )
                .toList();

        }

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

    public Sequence getById(Long id, User user) {
        return studentSequenceUtilsService.getSequenceByIdAndUserId(id, user);
    }




}
