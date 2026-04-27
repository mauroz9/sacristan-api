package com.sacristan.api.interfaces.student.biblioteca;

import com.sacristan.api.global.entities.content.category.Category;
import com.sacristan.api.global.entities.content.category.CategoryModelService;
import com.sacristan.api.global.entities.content.sequence.Sequence;
import com.sacristan.api.global.entities.content.sequence.SequenceModelService;
import com.sacristan.api.global.entities.content.sequence.SequenceSpecification;
import com.sacristan.api.global.entities.users.student.StudentModelService;
import com.sacristan.api.global.entities.users.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BibliotecaService {

    private final CategoryModelService categoryModelService;
    private final SequenceModelService sequenceModelService;
    private final StudentModelService studentModelService;

    public Page<Category> getCategories(Pageable pageable) {
        return categoryModelService.pageAll(pageable);
    }

    public Page<Sequence> getSequences(Pageable pageable,Long categoryId, String search, User user) {
        return studentModelService.findByFilters(pageable, user.getId(), categoryId, search);
    }

    public Integer getAssignedSequences(Category c) {
        return sequenceModelService.getSequencesByCategory(c);
    }
}
