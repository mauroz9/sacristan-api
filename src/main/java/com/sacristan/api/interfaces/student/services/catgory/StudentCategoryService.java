package com.sacristan.api.interfaces.student.services.catgory;

import com.sacristan.api.global.models.Category;
import com.sacristan.api.global.models.Sequence;
import com.sacristan.api.global.models.user.User;
import com.sacristan.api.interfaces.student.dtos.category.LibraryCategoryDTO;
import com.sacristan.api.interfaces.student.services.user.StudentUserUtilsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentCategoryService {

    private final StudentUserUtilsService studentUserUtilsService;
    private final StudentCategoryUtilsService categoryUtilsService;

    public Page<LibraryCategoryDTO> list(
            Pageable pageable,
            User user
    ) {
        List<Sequence> sequences = studentUserUtilsService.getSequencesByUserId(user);
        List<Category> categories = sequences.stream()
                .map(Sequence::getCategory)
                .distinct()
                .toList();

        if (categories.isEmpty()) {
            return new PageImpl<>(new ArrayList<>());
        }

        List<LibraryCategoryDTO> categoryDTOs = new ArrayList<>(
                categories.stream()
                        .map(c -> LibraryCategoryDTO.from(c, categoryUtilsService.countSequencesByCategory(c, sequences)))
                        .toList()
        );

        categoryDTOs.addFirst(
                new LibraryCategoryDTO(
                        null,
                        "Todas",
                        categoryUtilsService.countSequencesByCategory(null, sequences)
                )
        );

        return new PageImpl<>(
                categoryDTOs,
                pageable,
                categoryDTOs.size()
        );
    }

}
